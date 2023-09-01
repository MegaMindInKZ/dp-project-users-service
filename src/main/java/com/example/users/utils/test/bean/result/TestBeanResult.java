package com.example.users.utils.test.bean.result;

import lombok.Data;

@Data
public class TestBeanResult implements TestResult{
    private String name;
    private long periodMillisecond;
    private boolean isSuccessful;
    private Object data;
}
