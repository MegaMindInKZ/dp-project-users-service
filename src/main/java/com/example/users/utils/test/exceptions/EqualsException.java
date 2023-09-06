package com.example.users.utils.test.exceptions;

public class EqualsException extends NotPassedException{
    public EqualsException(Object value, Object notExpected) {
        super(String.format("Not expected: %s\n Value: %s", notExpected.toString(), value.toString()));
    }
}
