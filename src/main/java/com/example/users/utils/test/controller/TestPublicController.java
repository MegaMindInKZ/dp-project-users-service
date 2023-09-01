package com.example.users.utils.test.controller;

import com.example.users.utils.test.annotations.*;

@Test
public class TestPublicController {
    @BeforeTestClass
    public void beforeTestClass1(){
        System.out.println("beforeTestClass1");
    }
    @BeforeTestClass
    public void beforeTestClass2(){
        System.out.println("beforeTestClass2");
    }
    @BeforeTest
    public void beforeTest1(){
        System.out.println("beforeTest1");
    }
    @BeforeTest
    public void beforeTest2(){
        System.out.println("beforeTest2");
    }
    @Test
    public void test1(){
        System.out.println("test1");
    }

    @Test
    public void test2(){
        System.out.println("test2");
    }

    @Test
    public void test3(){
        System.out.println("test3");
    }
    @AfterTest
    public void afterTest1(){
        System.out.println("afterTest1");
    }
    @AfterTest
    public void afterTest2(){
        System.out.println("afterTest2");
    }
    @AfterTestClass
    public void afterTestClass1(){
        System.out.println("afterTestClass1");
    }
    @AfterTestClass
    public void afterTestClass2(){
        System.out.println("afterTestClass2");
    }
    @Test
    public void duman(){
        System.out.println("duman");
    }
}
