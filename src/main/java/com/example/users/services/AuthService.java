package com.example.users.services;

import com.example.users.beans.Response;
import com.example.users.exceptions.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface AuthService {
    Response register(Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response);
    Response login(Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response);
    Response uploadAccessToken(Map<String, Object> requestBodyParameters, HttpServletRequest request, HttpServletResponse response);
}
