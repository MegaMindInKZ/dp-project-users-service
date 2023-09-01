package com.example.users.utils.test.bean;

import lombok.Data;
@Data
public abstract class TestAbstractBean {
    private String uuid;
    private String definition;
    private String key;
    private String beanName;
    protected String type;
}
