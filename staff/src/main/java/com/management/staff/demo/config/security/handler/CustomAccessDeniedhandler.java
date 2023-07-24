package com.management.staff.demo.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.management.staff.demo.common.entity.ResultWrapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
* 认证用户无权限处理器
* */
@Component
public class CustomAccessDeniedhandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        String result = JSON.toJSONString(ResultWrapper.error().message("无权限访问，请联系管理员"));
        servletOutputStream.write(result.getBytes(StandardCharsets.UTF_8));
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
