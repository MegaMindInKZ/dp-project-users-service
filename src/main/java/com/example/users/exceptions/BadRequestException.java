package com.example.users.exceptions;

public class BadRequestException extends RuntimeException{
    Object errorObject;
    public BadRequestException(Object errorObject) {
        this.errorObject = errorObject;
    }
}
