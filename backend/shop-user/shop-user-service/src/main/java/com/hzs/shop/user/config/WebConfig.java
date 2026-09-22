package com.hzs.shop.user.config;

import com.hzs.shop.user.service.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 220419
 * @description
 * @date 2026/9/22
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")           // 拦截所有/api/请求
                .excludePathPatterns(
                        // 公开接口
                        "/api/user/login",
                        "/api/user/register",
                        "/swagger-ui/**",
                        "/v3/**",
                        "/doc.html",
                        "/uploads/**"
                );
    }
}
