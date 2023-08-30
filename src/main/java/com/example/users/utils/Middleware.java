package com.example.users.utils;

import com.example.users.exceptions.*;
import com.example.users.exceptions.log.LoggableBehaviour;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@Component
public class Middleware {
    public Object handle(Object service, Map<String, Object> requestBodyParameters, HttpServletRequest request, HttpServletResponse response, String functionName){
        try{
            Method method = service.getClass().getMethod(functionName, Map.class, HttpServletRequest.class, HttpServletResponse.class);
            response.setStatus(HttpServletResponse.SC_OK);
            return method.invoke(service, requestBodyParameters, request, response);
        }
        catch (Exception exception) {
            if(exception instanceof InvocationTargetException){
                exception = (Exception) exception.getCause();
            }
            if(!(exception instanceof CustomException)){
                exception = new ServiceException(exception);
            }
            return handleCustomException((CustomException)exception, response);
        }
    }
    private Object handleCustomException(CustomException exception, HttpServletResponse response){
        exception.logMessage(System.out);
        response.setStatus(exception.getHTTPStatus());
        return exception.getCaution();
    }
}
