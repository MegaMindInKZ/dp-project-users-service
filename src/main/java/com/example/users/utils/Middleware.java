package com.example.users.utils;

import com.example.users.beans.Response;
import com.example.users.components.interceptors.LoggerInterceptor;
import com.example.users.utils.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@Component
public class Middleware {

    private static Logger log = LoggerFactory.getLogger(Middleware.class);

    public Response handle(Object service, Map<String, Object> requestBodyParameters, HttpServletRequest request, HttpServletResponse response, String functionName){
        try{
            Method method = service.getClass().getMethod(functionName, Map.class, HttpServletRequest.class, HttpServletResponse.class);
            response.setStatus(HttpServletResponse.SC_OK);
            return (Response)method.invoke(service, requestBodyParameters, request, response);
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
    private Response handleCustomException(CustomException exception, HttpServletResponse httpServletResponse){
        if(exception.getLog() != null)
            log.error(exception.getLog().toString());
        Response response = new Response();
        response.setStatusCode(exception.getHTTPStatus());
        response.setResult(exception.getCaution());
        return response;
    }
}
