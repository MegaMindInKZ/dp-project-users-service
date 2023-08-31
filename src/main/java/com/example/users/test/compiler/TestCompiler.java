package com.example.users.test.compiler;

import com.example.users.test.bean.TestAbstractBean;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.InvocationTargetException;

public abstract class TestCompiler {
    protected ConfigurableApplicationContext applicationContext;
    protected TestAbstractBean testBean;
    protected abstract Object test();
    protected abstract void beforeTest() throws InvocationTargetException, IllegalAccessException;
    protected abstract void afterTest() throws InvocationTargetException, IllegalAccessException;

    public Object invoke(){
        Object result = null;
        try{
            beforeTest();
            result = test();
            afterTest();
        }catch (InvocationTargetException targetException){

        }catch (Exception ignored){}
        return result;
    }

    public void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setTestBean(TestAbstractBean testBean) {
        this.testBean = testBean;
    }
}
