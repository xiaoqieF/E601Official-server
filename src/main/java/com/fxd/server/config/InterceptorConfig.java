package com.fxd.server.config;

import com.fxd.server.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 拦截器注册配置类
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 博客前台的请求不用拦截
        // 拦截所有以private/开头的请求，进行token验证
        InterceptorRegistration registration = registry.addInterceptor(new JWTInterceptor());
        registration.addPathPatterns("/private/**");
    }
}