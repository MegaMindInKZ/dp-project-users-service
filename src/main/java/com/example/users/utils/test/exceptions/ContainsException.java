package com.example.users.utils.test.exceptions;

public class ContainsException extends NotPassedException{
    public ContainsException(String value, String substring) {
        super(String.format("Value: %s\n Found: %s", value, substring));
    }
}
