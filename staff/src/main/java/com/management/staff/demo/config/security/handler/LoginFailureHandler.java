package com.management.staff.demo.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.management.staff.demo.common.entity.ResultWrapper;
import com.management.staff.demo.config.security.exception.CustomAuthenticationException;
import com.management.staff.demo.entity.Users;
import net.sf.jsqlparser.expression.JsonAggregateFunction;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        设置编码格式
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        String message = null;
        int code = 500;
        //判断异常类型
        if (exception instanceof AccountExpiredException) {
            message = "账户过期，登录失败";
        } else if (exception instanceof BadCredentialsException) {
            message = "用户名或密码错误，登录失败";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "密码过期，登录失败";
        } else if (exception instanceof DisabledException) {
            message = "账户禁用，登录失败";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            message = "账户不存在，登录失败";
        } else if (exception instanceof LockedException) {
            message = "账户锁定，登录失败";
        } else if (exception instanceof CustomAuthenticationException) {
            message = exception.getMessage();
            code = 600;
        } else {
            message = "登录失败";
        }

        String result = JSON.toJSONString(ResultWrapper.error(code).message(message));
        servletOutputStream.write(result.getBytes(StandardCharsets.UTF_8));
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
