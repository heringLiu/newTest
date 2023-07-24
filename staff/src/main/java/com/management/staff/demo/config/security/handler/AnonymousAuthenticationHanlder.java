package com.management.staff.demo.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.management.staff.demo.common.entity.ResultWrapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
 * 匿名用户无权限处理器
 * */
@Component
public class AnonymousAuthenticationHanlder implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        String result = JSON.toJSONString(ResultWrapper.error().message("匿名用户无权限访问"));
        servletOutputStream.write(result.getBytes(StandardCharsets.UTF_8));
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
