package com.example.users.utils;

import com.example.users.beans.Response;
import com.example.users.exceptions.BadRequestException;
import com.example.users.exceptions.ForbiddenException;
import com.example.users.exceptions.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class Middleware {
    public static Response handle(Object service, Map<String, Object> requestBodyParameters, HttpServletRequest request, HttpServletResponse response, String functionName){
        try{
            Method method = service.getClass().getMethod(functionName, Map.class, HttpServletRequest.class, HttpServletResponse.class);
            return (Response) method.invoke(service, requestBodyParameters, request, response);
        }catch (ForbiddenException forbiddenException){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new Response(-5, "Forbidden", null);
        }
        catch (BadRequestException badRequestException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new Response(-4, "Bad Request", badRequestException.getErrorObject());
        }
        catch (ServiceException serviceException){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Response(-3, "Service Exception", null);
        }
        catch (Exception exception) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Response(-3, "Service Exception", null);
        }
    }
}
