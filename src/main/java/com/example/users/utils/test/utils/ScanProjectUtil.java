package com.example.users.utils.test.utils;

import com.example.users.utils.exceptions.ServiceException;
import com.example.users.utils.test.annotations.Test;
import com.example.users.utils.test.bean.AbstractTestBean;
import com.example.users.utils.test.bean.TestClassBean;
import com.example.users.utils.test.bean.TestMethodBean;
import com.example.users.utils.test.bean.result.TestBeanResult;
import com.example.users.utils.test.bean.result.TestErrorResult;
import com.example.users.utils.test.bean.result.TestResult;
import com.example.users.utils.test.compiler.TestMethodCompiler;

import java.lang.reflect.Method;
import java.util.UUID;

public class ScanProjectUtil {
    public static final String CLASS_TEST_BEAN_TYPE = "class";
    public static final String METHOD_TEST_BEAN_TYPE = "method";
    public static AbstractTestBean getTextBean(Object bean){
        AbstractTestBean testBean;
        String uuid = UUID.randomUUID().toString();
        if(bean instanceof Class<?>){
            Class<?> clazz = (Class<?>) bean;
            testBean = parse(clazz);
        } else if (bean instanceof Method) {
            Method method = (Method) bean;
            testBean = parse(method);
        }else{
            throw new ServiceException(new RuntimeException("Test can be only method or class"));
        }
        testBean.setUuid(uuid);
        return testBean;
    }
    private static AbstractTestBean parse(Method method){
        TestMethodBean testMethodBean = new TestMethodBean();
        String definition = method.getAnnotation(Test.class).definition();

        testMethodBean.setDefinition(definition);
        testMethodBean.setType(METHOD_TEST_BEAN_TYPE);
        testMethodBean.setKey(method.getName());

        return testMethodBean;
    }

    private static AbstractTestBean parse(Class<?> clazz){
        TestClassBean testClassBean = new TestClassBean();
        String definition = clazz.getAnnotation(Test.class).definition();

        testClassBean.setDefinition(definition);
        testClassBean.setType(CLASS_TEST_BEAN_TYPE);
        testClassBean.setKey(clazz.getSimpleName());

        return testClassBean;
    }

    public static TestBeanResult getMethodBeanResult(Method method, Object object){
        TestBeanResult testResult = new TestBeanResult();
        testResult.setName(method.getName());

        long start = System.currentTimeMillis();
        try{
            method.invoke(object);
            testResult.setSuccessful(true);
        }catch (Exception e){
            testResult.setData(new TestErrorResult(e, method));
        }
        long end = System.currentTimeMillis();

        testResult.setPeriodMillisecond(end - start);
        return testResult;
    }

    public static TestResult getClassBeanResult(Method method, Object object){
        TestMethodCompiler methodCompiler = new TestMethodCompiler();
        methodCompiler.setMethod(method);
        methodCompiler.setObject(object);
        TestResult testResult = methodCompiler.invoke();

        return testResult;
    }
}
