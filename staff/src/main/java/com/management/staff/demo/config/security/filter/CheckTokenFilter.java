package com.management.staff.demo.config.security.filter;

import com.management.staff.demo.config.redis.RedisService;
import com.management.staff.demo.config.security.exception.CustomAuthenticationException;
import com.management.staff.demo.config.security.handler.LoginFailureHandler;
import com.management.staff.demo.config.security.service.CustomUserDetailsService;
import com.management.staff.demo.utils.JwtUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Component
public class CheckTokenFilter extends OncePerRequestFilter {

    // 登录地址
    @Value("${request.login.url}")
    private String loginUrl;
    // 登录地址
    @Value("${request.swagger.url}")
    private String swaggerUrl;
    @Resource
    private RedisService redisService;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    CustomUserDetailsService customUserDetailsService;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String url = request.getRequestURI();

            if (url.startsWith("/emp") || (url.startsWith("/users") && !url.equals(loginUrl))) {
                this.validateToken(request);
            }
        } catch (AuthenticationException e) {
            loginFailureHandler.onAuthenticationFailure(request, response, e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //
        doFilter(request, response, filterChain);

    }

    private void validateToken(HttpServletRequest request) throws Exception {
        //获取token
        String token = request.getHeader("token");
        if (ObjectUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        if (ObjectUtils.isEmpty(token)) {
            throw new CustomAuthenticationException("token不存在");
        }
        // 获取reids中的token
        String tokenkey = "token_" + token;
        String redisToken = redisService.get(tokenkey);
        // 判断是否过期
        if (ObjectUtils.isEmpty(redisToken)) {
            throw new CustomAuthenticationException("token验证失败");
        }

        if (!token.equals(redisToken)) {
            throw new CustomAuthenticationException("token已过期");
        }

        String username = jwtUtils.getUsernameFromToken(token);
        if (ObjectUtils.isEmpty(username)) {
            throw new CustomAuthenticationException("token解析失败");
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new CustomAuthenticationException("token验证失败");
        }
        // 创建用户身份认证信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // 设置请求信息
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 认证信息交给spring security 上下文
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
