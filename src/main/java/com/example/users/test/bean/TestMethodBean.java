package com.example.users.test.bean;

import com.example.users.test.utils.ScanProjectUtil;
import lombok.Data;

@Data
public class TestMethodBean extends TestAbstractBean {
    private String className;
    public TestMethodBean(){
        type = ScanProjectUtil.METHOD_TEST_BEAN_TYPE;
    }
}
