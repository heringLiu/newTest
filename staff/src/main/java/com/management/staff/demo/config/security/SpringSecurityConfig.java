package com.management.staff.demo.config.security;

import com.management.staff.demo.config.security.handler.AnonymousAuthenticationHanlder;
import com.management.staff.demo.config.security.handler.LoginFailureHandler;
import com.management.staff.demo.config.security.handler.LoginSuccessHandler;
import com.management.staff.demo.config.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private AnonymousAuthenticationHanlder anonymousAuthenticationHanlder;

    @Resource
    private AccessDeniedHandler accessDeniedHandler;

    @Resource
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    * 配置认证处理器
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /*
    * 登录认证处理器
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*.html","/*.svg","/*.png","/*.js","/*.css","/csrf").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin()
                .loginProcessingUrl("/users/login")// 登录请求的url
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 不创建session
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(anonymousAuthenticationHanlder)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .cors(); //支持跨域
        http.headers().frameOptions().sameOrigin();

    }
}


