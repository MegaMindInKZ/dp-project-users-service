package com.example.users.utils;

import com.example.users.beans.Response;
import com.example.users.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class Middleware {
    public static Response handle(Object service, Map<String, Object> requestBodyParameters, HttpServletRequest request, HttpServletResponse response, String functionName){
        try{
            response.setStatus(HttpServletResponse.SC_OK);
            Method method = service.getClass().getMethod(functionName, Map.class, HttpServletRequest.class, HttpServletResponse.class);
            return (Response) method.invoke(service, requestBodyParameters, request, response);
        }
        catch (Exception exception) {
            if(exception instanceof CustomException){
                return handleCustomException((CustomException) exception, response);
            }
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Response(-3, "ServiceException!", null);
        }
    }
    private static Response handleCustomException(CustomException exception, HttpServletResponse response){
        if(exception instanceof LoggableCustomException) {
            //@TODO: logging error
        }
        response.setStatus(exception.getHTTPStatus());
        return new Response(exception.getHTTPStatus(), exception.getClass().getSimpleName() + "!", exception.getCaution());
    }
}
