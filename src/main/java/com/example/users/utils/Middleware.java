package com.example.users.utils;

import com.example.users.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.Map;

public class Middleware {
    public static Object handle(Object service, Map<String, Object> requestBodyParameters, HttpServletRequest request, HttpServletResponse response, String functionName){
        try{
            response.setStatus(HttpServletResponse.SC_OK);
            Method method = service.getClass().getMethod(functionName, Map.class, HttpServletRequest.class, HttpServletResponse.class);
            return method.invoke(service, requestBodyParameters, request, response);
        }
        catch (Exception exception) {
            if(exception instanceof CustomException){
                return handleCustomException((CustomException) exception, response);
            }
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }
    private static Object handleCustomException(CustomException exception, HttpServletResponse response){
        if(exception instanceof LoggableCustomException) {
            //@TODO: logging error
        }
        response.setStatus(exception.getHTTPStatus());
        return exception.getCaution();
    }
}
