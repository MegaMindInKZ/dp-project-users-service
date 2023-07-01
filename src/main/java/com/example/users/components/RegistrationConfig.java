package com.example.users.components;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class RegistrationConfig implements WebMvcConfigurer {
    LoggerInterceptor loggerInterceptor;
    JWTInterceptor jwtInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor).addPathPatterns("/**");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns("/users/public/**");

    }
}
