package com.example.users.utils.test.exceptions;

public class NotContainsException extends NotPassedException{
    public NotContainsException(String value, String substring) {
        super(String.format("Value: %s\n Not found: %s", value, substring));
    }
}
