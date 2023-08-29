package com.example.users.utils;

import com.example.users.exceptions.*;
import com.example.users.exceptions.log.LoggableBehaviour;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Component
public class Middleware {
    public Object handle(Object service, Map<String, Object> requestBodyParameters, HttpServletRequest request, HttpServletResponse response, String functionName){
        try{
            response.setStatus(HttpServletResponse.SC_OK);
            Method method = service.getClass().getMethod(functionName, Map.class, HttpServletRequest.class, HttpServletResponse.class);
            return method.invoke(service, requestBodyParameters, request, response);
        }
        catch (Exception exception) {
            if(!(exception instanceof CustomException)){
                exception = new ServiceException(exception);
            }
            return handleCustomException((CustomException)exception, response);
        }
    }
    private Object handleCustomException(CustomException exception, HttpServletResponse response){
        //@TODO logging
        response.setStatus(exception.getHTTPStatus());
        return exception.getCaution();
    }
}
