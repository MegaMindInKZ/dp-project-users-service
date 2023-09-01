package com.example.users.utils.test.compiler;

import com.example.users.utils.test.bean.TestAbstractBean;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractTestCompiler {
    protected TestAbstractBean testBean;
    protected Object object;

    protected abstract Object test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    protected abstract void beforeTest() throws InvocationTargetException, IllegalAccessException;
    protected abstract void afterTest() throws InvocationTargetException, IllegalAccessException;

    public Object invoke(){
        Object result = null;
        try{
            beforeTest();
            result = test();
            afterTest();
        }catch (InvocationTargetException targetException){
            targetException.printStackTrace();
        }catch (Exception ignored){
            ignored.printStackTrace();
        }
        return result;
    }
    public void setTestBean(TestAbstractBean testBean) {
        this.testBean = testBean;
    }
    public void setObject(Object object) {
        this.object = object;
    }
}
