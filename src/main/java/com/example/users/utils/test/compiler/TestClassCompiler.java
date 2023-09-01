package com.example.users.utils.test.compiler;

import com.example.users.utils.test.annotations.AfterTestClass;
import com.example.users.utils.test.annotations.BeforeTestClass;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Data
public class TestClassCompiler extends AbstractTestCompiler {
    private List<Method> methods;
    @Override
    protected Object test() {
        for(Method method: methods){
            TestMethodCompiler testMethodCompiler = new TestMethodCompiler();
            testMethodCompiler.setMethod(method);
            testMethodCompiler.setObject(object);
            testMethodCompiler.invoke();
        }
        return null;
    }

    @Override
    protected void beforeTest() throws InvocationTargetException, IllegalAccessException {
        Method[] methods = object.getClass().getDeclaredMethods();
        for(Method method: methods){
            if(method.isAnnotationPresent(BeforeTestClass.class))
                method.invoke(object);
        }
    }

    @Override
    protected void afterTest() throws InvocationTargetException, IllegalAccessException {
        Method[] methods = object.getClass().getDeclaredMethods();
        for(Method method: methods){
            if(method.isAnnotationPresent(AfterTestClass.class))
                method.invoke(object);
        }
    }
}
