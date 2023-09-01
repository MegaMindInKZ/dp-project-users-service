package com.example.users.utils.test.compiler;

import com.example.users.utils.test.annotations.Test;
import com.example.users.utils.test.bean.TestAbstractBean;
import com.example.users.utils.test.bean.TestMethodBean;
import com.example.users.utils.test.utils.ScanProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestCompilerFactory {
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    public Object invoke(TestAbstractBean testAbstractBean){
        TestClassCompiler compiler = new TestClassCompiler();
        List<Method> methods = new ArrayList<>();
        Object clazz = applicationContext.getBean(testAbstractBean.getBeanName());
        if(testAbstractBean.getType().equals(ScanProjectUtil.METHOD_TEST_BEAN_TYPE)){
            Method method = null;
            try {
                method = clazz.getClass().getMethod(testAbstractBean.getKey());
            } catch (NoSuchMethodException ignored) {}
            methods.add(method);
        }
        else if (testAbstractBean.getType().equals(ScanProjectUtil.CLASS_TEST_BEAN_TYPE)){
            Method[] methods1 = clazz.getClass().getDeclaredMethods();
            for(Method method: methods1){
                if(method.isAnnotationPresent(Test.class))
                    methods.add(method);
            }
        }
        compiler.setObject(clazz);
        compiler.setTestBean(testAbstractBean);
        compiler.setMethods(methods);

        return compiler.invoke();
    }
}
