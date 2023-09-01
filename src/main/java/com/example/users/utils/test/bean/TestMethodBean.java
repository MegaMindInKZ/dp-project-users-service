package com.example.users.utils.test.bean;

import com.example.users.utils.test.utils.ScanProjectUtil;
import lombok.Data;

@Data
public class TestMethodBean extends TestAbstractBean {
    public TestMethodBean(){
        type = ScanProjectUtil.METHOD_TEST_BEAN_TYPE;
    }
}
