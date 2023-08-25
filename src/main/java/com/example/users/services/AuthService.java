package com.example.users.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public interface AuthService {
    Object register(Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response);
    Object login(Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response);
    Object uploadAccessToken(Map<String, Object> requestBodyParameters, HttpServletRequest request, HttpServletResponse response);
}
