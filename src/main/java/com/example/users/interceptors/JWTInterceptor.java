package com.example.users.interceptors;

import com.example.users.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String authorization = request.getHeader("Authorization");
            String[] auths = authorization.split(" ");
            String token = auths[1];
            JWTUtils.verify(token);
            return true;
        }catch (Exception e){
            response.setStatus(401);
        }
        return false;
    }
}
