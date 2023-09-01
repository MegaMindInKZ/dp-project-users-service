package com.example.users.utils.test.compiler;

import com.example.users.utils.test.annotations.AfterTestClass;
import com.example.users.utils.test.annotations.BeforeTestClass;
import com.example.users.utils.test.utils.ScanProjectUtil;
import com.example.users.utils.test.utils.TestResult;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Data
public class TestClassCompiler extends AbstractTestCompiler {
    private List<Method> methods;
    @Override
    protected Object test() {
        List<TestResult> result = new ArrayList<>();
        for(Method method: methods){
            result.add(ScanProjectUtil.getClassBeanResult(method, object));
        }
        return result;
    }

    @Override
    protected List<TestResult> beforeTest(){
        Method[] methods = object.getClass().getDeclaredMethods();
        List<TestResult> result = new ArrayList<>();

        for(Method method: methods){
            if(method.isAnnotationPresent(BeforeTestClass.class)) {
                result.add(ScanProjectUtil.getMethodBeanResult(method, object));
            }
        }
        return result;
    }

    @Override
    protected List<TestResult> afterTest(){
        List<TestResult> result = new ArrayList<>();

        Method[] methods = object.getClass().getDeclaredMethods();
        for(Method method: methods){
            if(method.isAnnotationPresent(AfterTestClass.class)) {
                result.add(ScanProjectUtil.getMethodBeanResult(method, object));

            }
        }
        return result;
    }
}
