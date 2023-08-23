package com.example.users.entities;

import com.example.users.annotations.Ignore;
import com.example.users.exceptions.ServiceException;
import com.example.users.utils.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class Table {
    public Map<String, Object> getMap(){
        try{
            Field[] fields = this.getClass().getDeclaredFields();
            Map<String, Object> map = new HashMap<>();

            for (Field field : fields) {
                String fieldName = field.getName();

                if (isIgnored(field))
                    continue;

                String getterMethodName = StringUtils.getGetterMethodNameByFieldName(fieldName);
                Object val = this.getClass().getMethod(getterMethodName).invoke(this);
                map.put(fieldName, val);
            }
            return map;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public Object getValueByFieldName(String fieldName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String getterMethodName = StringUtils.getGetterMethodNameByFieldName(fieldName);
        return this.getClass().getMethod(getterMethodName).invoke(this);
    }

    public void setValueByFieldName(String fieldName, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Field field = this.getClass().getDeclaredField(fieldName);

        String setterMethodName = StringUtils.getSetterMethodNameByFieldName(fieldName);
        if(value.getClass().getTypeName().equals(Integer.class.getName()) && field.getType().getName().equals(Long.class.getName())) {
            value = ((Integer)value).longValue();
        }

        this.getClass().getMethod(setterMethodName, value.getClass()).invoke(this, value);
    }

    private boolean isIgnored(Field field){
        return field.isAnnotationPresent(Ignore.class);
    }
}
