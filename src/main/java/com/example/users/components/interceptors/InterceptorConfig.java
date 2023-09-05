package com.example.users.components.interceptors;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    LoggerInterceptor loggerInterceptor;
    JWTInterceptor jwtInterceptor;
    CrossInterceptor crossInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(crossInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loggerInterceptor).addPathPatterns("/**");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns("/users/public/**", "/users/test/**", "/error");

    }
}
