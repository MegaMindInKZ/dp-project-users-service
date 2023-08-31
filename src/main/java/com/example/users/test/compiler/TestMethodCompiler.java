package com.example.users.test.compiler;

import com.example.users.exceptions.BadRequestException;
import com.example.users.test.bean.TestMethodBean;
import com.example.users.test.utils.ScanProjectUtil;

public class TestMethodCompiler extends TestCompiler {
    @Override
    protected Object test() {
        if(!testBean.getType().equals(ScanProjectUtil.METHOD_TEST_BEAN_TYPE)){
            throw new BadRequestException("Not allowed call method test for not method testBean!");
        }
        TestMethodBean methodBean = (TestMethodBean) testBean;
        return null;
    }

    @Override
    protected void beforeTest() {

    }

    @Override
    protected void afterTest() {

    }
}
