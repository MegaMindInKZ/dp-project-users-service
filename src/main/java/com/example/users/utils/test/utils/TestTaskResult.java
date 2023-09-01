package com.example.users.utils.test.utils;

import lombok.Data;

import java.util.List;

@Data
public class TestTaskResult implements TestResult{
    private String name;
    private List<TestResult> beforeTestResult;
    private Object testResult;
    private List<TestResult> afterTestResult;
}
