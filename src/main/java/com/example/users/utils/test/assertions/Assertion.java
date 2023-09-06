package com.example.users.utils.test.assertions;

import com.example.users.utils.test.exceptions.ContainsException;
import com.example.users.utils.test.exceptions.EqualsException;
import com.example.users.utils.test.exceptions.NotContainsException;
import com.example.users.utils.test.exceptions.NotEqualsException;

public class Assertion {
    public static void assertNotEquals(Object value, Object notExpected){
        if(value.equals(notExpected))
            throw new EqualsException(value, notExpected);
    }

    public static void assertEquals(Object value, Object notExpected){
        if(!value.equals(notExpected))
            throw new NotEqualsException(value, notExpected);
    }

    public static void assertContains(String value, String substring){
        if(value.contains(substring)){
            throw new NotContainsException(value, substring);
        }
    }
    public static void assertNotContains(String value, String substring){
        if(!value.contains(substring)){
            throw new ContainsException(value, substring);
        }
    }
}
