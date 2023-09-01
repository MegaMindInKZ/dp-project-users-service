package com.example.users.utils.test.utils;

import lombok.Data;

@Data
public class TestBeanResult implements TestResult{
    private long periodMillisecond;
    private boolean isSuccessful;
    private Object data;
}
