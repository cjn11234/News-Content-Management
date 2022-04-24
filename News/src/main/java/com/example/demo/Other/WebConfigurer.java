package com.example.demo.Other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(loginHandlerInterceptor);
        // 拦截路径
        ir.addPathPatterns("/*");
        // 不拦截路径
        List<String> irs = new ArrayList<String>();
        irs.add("/");
        irs.add("/User/register");
        ir.excludePathPatterns(irs);
    }
}
