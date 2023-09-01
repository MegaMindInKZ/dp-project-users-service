package com.example.users.utils.test.compiler;

import com.example.users.utils.test.annotations.AfterTest;
import com.example.users.utils.test.annotations.BeforeTest;
import com.example.users.utils.test.utils.ScanProjectUtil;
import com.example.users.utils.test.utils.TestResult;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Data
public class TestMethodCompiler extends AbstractTestCompiler {
    Method method;
    @Override
    protected Object test(){
        return ScanProjectUtil.getMethodBeanResult(method, object);
    }

    @Override
    protected List<TestResult> beforeTest(){
        Method[] methods = object.getClass().getDeclaredMethods();
        List<TestResult> result = new ArrayList<>();

        for(Method method: methods){
            if(method.isAnnotationPresent(BeforeTest.class)) {
                result.add(ScanProjectUtil.getMethodBeanResult(method, object));
            }
        }
        return result;
    }

    @Override
    protected List<TestResult> afterTest(){
        Method[] methods = object.getClass().getDeclaredMethods();
        List<TestResult> result = new ArrayList<>();

        for(Method method: methods){
            if(method.isAnnotationPresent(AfterTest.class)) {
                result.add(ScanProjectUtil.getMethodBeanResult(method, object));
            }
        }
        return result;
    }
}
