package com.example.users.utils.test.compiler;

import com.example.users.utils.test.utils.TestResult;
import com.example.users.utils.test.utils.TestTaskResult;

import java.util.List;

public abstract class AbstractTestCompiler {
    protected Object object;

    protected abstract Object test();
    protected abstract List<TestResult> beforeTest();
    protected abstract List<TestResult> afterTest();

    public TestTaskResult invoke(){
        TestTaskResult result = new TestTaskResult();
        result.setName(object.getClass().getSimpleName());
        result.setBeforeTestResult(beforeTest());
        result.setTestResult(test());
        result.setAfterTestResult(afterTest());
        return result;
    }
    public void setObject(Object object) {
        this.object = object;
    }
}
