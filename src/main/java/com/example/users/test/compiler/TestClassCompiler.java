package com.example.users.test.compiler;

import com.example.users.exceptions.BadRequestException;
import com.example.users.test.annotations.AfterTestClass;
import com.example.users.test.annotations.BeforeTestClass;
import com.example.users.test.bean.TestClassBean;
import com.example.users.test.utils.ScanProjectUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClassCompiler extends TestCompiler {
    @Override
    protected Object test() {
        if(!testBean.getType().equals(ScanProjectUtil.CLASS_TEST_BEAN_TYPE)){
            throw new BadRequestException("Not allowed call class test for not class testBean!");
        }
        TestClassBean methodBean = (TestClassBean) testBean;
        return null;
    }

    @Override
    public void beforeTest() throws InvocationTargetException, IllegalAccessException {
        Object clazz = applicationContext.getBean(testBean.getKey());
        Method[] methods = clazz.getClass().getDeclaredMethods();
        for(Method method: methods){
            if(!method.isAnnotationPresent(BeforeTestClass.class))
                continue;
            method.invoke(clazz);
        }
    }

    @Override
    public void afterTest() throws InvocationTargetException, IllegalAccessException {
        Object clazz = applicationContext.getBean(testBean.getKey());
        Method[] methods = clazz.getClass().getDeclaredMethods();
        for(Method method: methods){
            if(!method.isAnnotationPresent(AfterTestClass.class))
                continue;
            method.invoke(clazz);
        }
    }
}
