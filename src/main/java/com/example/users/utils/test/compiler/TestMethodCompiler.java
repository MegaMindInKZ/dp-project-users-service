package com.example.users.utils.test.compiler;

import com.example.users.utils.exceptions.BadRequestException;
import com.example.users.utils.test.annotations.AfterTest;
import com.example.users.utils.test.annotations.BeforeTest;
import com.example.users.utils.test.bean.TestMethodBean;
import com.example.users.utils.test.utils.ScanProjectUtil;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Data
public class TestMethodCompiler extends AbstractTestCompiler {
    Method method;
    @Override
    protected Object test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        method.invoke(object);
        return null;
    }

    @Override
    protected void beforeTest() throws InvocationTargetException, IllegalAccessException {
        Method[] methods = object.getClass().getDeclaredMethods();
        for(Method method: methods){
            if(method.isAnnotationPresent(BeforeTest.class))
                method.invoke(object);
        }
    }

    @Override
    protected void afterTest() throws InvocationTargetException, IllegalAccessException {
        Method[] methods = object.getClass().getDeclaredMethods();
        for(Method method: methods){
            if(method.isAnnotationPresent(AfterTest.class))
                method.invoke(object);
        }
    }
}
