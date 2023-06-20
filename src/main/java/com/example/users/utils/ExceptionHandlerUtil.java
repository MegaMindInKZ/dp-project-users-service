package com.example.users.utils;

import com.example.users.beans.Response;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ExceptionHandlerUtil {
    public static Response exceptionHandler(Object service, Map<String, Object> requestBodyParameters, HttpServletResponse response, String functionName){
        try{
            Method method = service.getClass().getMethod(functionName, Map.class, HttpServletResponse.class);
            return (Response) method.invoke(service, requestBodyParameters, response);
        }catch (InvocationTargetException e) {
            return new Response(-2, "Service Exception", e.getMessage());
        }catch (Exception exception) {
            return new Response(-3, "Exception", exception.getStackTrace());
        }
    }
}
