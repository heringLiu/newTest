package com.management.staff.demo.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.management.staff.demo.common.constant.ResultCode;
import com.management.staff.demo.config.redis.RedisService;
import com.management.staff.demo.entity.Users;
import com.management.staff.demo.utils.JwtUtils;
import com.management.staff.demo.utils.LoginResult;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
* 登录成功处理器
* */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        设置编码格式
        response.setContentType("application/json;charset=utf-8");
        //
        Users users = (Users) authentication.getPrincipal();
        // 生成token
        String token = jwtUtils.generateToken(users);
        long expireTime = Jwts.parser()
                .setSigningKey(jwtUtils.getSecret())
                .parseClaimsJws(token.replace("jwt_", ""))
                .getBody().getExpiration().getTime();
        LoginResult loginResult = new LoginResult(users.getId(), 200, token, expireTime);

        String result =  JSON.toJSONString(loginResult, SerializerFeature.DisableCircularReferenceDetect);
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(result.getBytes(StandardCharsets.UTF_8));
        servletOutputStream.flush();
        servletOutputStream.close();

        // token 写入redis
        String tokenKey = "token_" + token;
        redisService.set(tokenKey, token, jwtUtils.getExpiration() / 1000);
    }
}
