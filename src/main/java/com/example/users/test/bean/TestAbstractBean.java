package com.example.users.test.bean;

import lombok.Data;
@Data
public abstract class TestAbstractBean {
    private String uuid;
    private String definition;
    private String key;
    protected String type;
}
