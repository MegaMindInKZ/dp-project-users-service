package com.example.users.utils.test.utils;

import lombok.Data;

@Data
public class TestBeanResult implements TestResult{
    private String name;
    private long periodMillisecond;
    private boolean isSuccessful;
    private Object data;
}
