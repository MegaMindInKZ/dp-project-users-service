package com.example.users.utils.test.components;

import com.example.users.utils.RequestBodyParamsUtils;
import com.example.users.utils.exceptions.NotFoundException;
import com.example.users.utils.test.annotations.Test;
import com.example.users.utils.test.bean.AbstractTestBean;
import com.example.users.utils.test.compiler.TestCompilerFactory;
import com.example.users.utils.test.utils.ScanProjectUtil;
import com.example.users.utils.test.utils.TestTaskResult;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TestComponent {
    @Autowired
    private ConfigurableApplicationContext applicationContext;
    @Autowired
    private TestCompilerFactory compilerFactory;
    private List<AbstractTestBean> testBeans;

    @PostConstruct
    public void init(){
        testBeans = new ArrayList<>();
        String[] beanNamesForAnnotation = applicationContext.getBeanNamesForAnnotation(Test.class);
        for(String beanName: beanNamesForAnnotation){
            Object bean = applicationContext.getBean(beanName);
            Class<?> clazz = bean.getClass();
            AbstractTestBean classTestBean = ScanProjectUtil.getTextBean(clazz);
            classTestBean.setBeanName(beanName);
            testBeans.add(classTestBean);
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method: methods){
                if(method.isAnnotationPresent(Test.class)){
                    AbstractTestBean methodTestBean = ScanProjectUtil.getTextBean(method);
                    methodTestBean.setBeanName(beanName);
                    testBeans.add(methodTestBean);
                }
            }
        }
    }

    public List<AbstractTestBean> getTestNameString(){
        return testBeans;
    }

    public TestTaskResult invoke(Map<String, Object> params){
        String uuid = RequestBodyParamsUtils.getString(params, "uuid", true, true);
        AbstractTestBean testBean = null;
        for(AbstractTestBean bean: testBeans){
            if(bean.getUuid().equals(uuid)){
                testBean = bean;
                break;
            }
        }
        if(testBean == null)
            throw new NotFoundException();


        return compilerFactory.invoke(testBean);
    }
}
