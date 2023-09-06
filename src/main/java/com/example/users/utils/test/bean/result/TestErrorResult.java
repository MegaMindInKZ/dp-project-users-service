package com.example.users.utils.test.bean.result;

import com.example.users.utils.exceptions.ServiceException;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Data
public class TestErrorResult implements TestResult{
    private int lineNumber;
    private String errorMessage;
    private String errorClass;
    private String className;
    private String methodName;

    public TestErrorResult(Exception e, Method method){
        if(!(e instanceof InvocationTargetException)){
            throw new ServiceException(e);
        }
        Exception exception = (Exception) e.getCause();
        errorMessage = exception.getMessage();
        errorClass = exception.getClass().getSimpleName();
        lineNumber = exception.getStackTrace()[1].getLineNumber();
        className = method.getDeclaringClass().getSimpleName();
        methodName = method.getName();
    }
}
