package com.example.users.test.components;

import com.example.users.test.annotations.Test;
import com.example.users.test.bean.TestAbstractBean;
import com.example.users.test.utils.ScanProjectUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestComponent {
    @Autowired
    private ConfigurableApplicationContext applicationContext;
    private List<TestAbstractBean> testBeans;

    @PostConstruct
    public void init(){
        testBeans = new ArrayList<>();
        String[] beanNamesForAnnotation = applicationContext.getBeanNamesForAnnotation(Test.class);
        for(String beanName: beanNamesForAnnotation){
            Object bean = applicationContext.getBean(beanName);
            Class<?> clazz = bean.getClass();
            TestAbstractBean classTestBean = ScanProjectUtil.getTextBean(clazz);
            testBeans.add(classTestBean);
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method: methods){
                if(method.isAnnotationPresent(Test.class)){
                    TestAbstractBean methodTestBean = ScanProjectUtil.getTextBean(method);
                    testBeans.add(methodTestBean);
                }
            }
        }
    }

    public List<TestAbstractBean> getTestNameString(){
        return testBeans;
    }
}
