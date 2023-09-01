package com.example.users.utils.test.compiler;

import com.example.users.utils.test.annotations.Test;
import com.example.users.utils.test.bean.AbstractTestBean;
import com.example.users.utils.test.utils.ScanProjectUtil;
import com.example.users.utils.test.utils.TestTaskResult;
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

    public TestTaskResult invoke(AbstractTestBean abstractTestBean){
        TestClassCompiler compiler = new TestClassCompiler();
        List<Method> methods = new ArrayList<>();
        Object clazz = applicationContext.getBean(abstractTestBean.getBeanName());
        if(abstractTestBean.getType().equals(ScanProjectUtil.METHOD_TEST_BEAN_TYPE)){
            Method method = null;
            try {
                method = clazz.getClass().getMethod(abstractTestBean.getKey());
            } catch (NoSuchMethodException ignored) {}
            methods.add(method);
        }
        else if (abstractTestBean.getType().equals(ScanProjectUtil.CLASS_TEST_BEAN_TYPE)){
            Method[] methods1 = clazz.getClass().getDeclaredMethods();
            for(Method method: methods1){
                if(method.isAnnotationPresent(Test.class))
                    methods.add(method);
            }
        }
        compiler.setObject(clazz);
        compiler.setMethods(methods);

        return compiler.invoke();
    }
}
