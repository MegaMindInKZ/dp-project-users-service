package com.example.users.utils.test.exceptions;

public class NotEqualsException extends NotPassedException{
    public NotEqualsException(Object value, Object expected) {
        super(String.format("Expected: %s\n Value: %s", expected.toString(), value.toString()));
    }
}
