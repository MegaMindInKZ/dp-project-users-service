package com.example.users.utils;

import com.example.users.entities.Table;
import com.example.users.exceptions.BadRequestException;
import com.example.users.exceptions.ServiceException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class RequestBodyParamsUtils {
    public static String getString(Map<String, Object> requestBodyParams, String key, boolean denyNull, boolean denyEmpty) throws ServiceException {
        String value = (String) requestBodyParams.get(key);
        if(denyNull && value == null)
            throw new BadRequestException(key + " cannot be null!");
        if(denyEmpty && value.isEmpty())
            throw new BadRequestException(key + " cannot be empty!");
        return value;
    }

    public static boolean isInstance(Object object, Field field, Table model){
        if(object.getClass().getTypeName().equals(Integer.class.getName()) && field.getType().getName().equals(Long.class.getName())) {
            return true;
        }

        return object.getClass().getTypeName().equals(field.getType().getName());
    }

}
