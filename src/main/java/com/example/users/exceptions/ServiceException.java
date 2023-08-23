package com.example.users.exceptions;

public class ServiceException extends RuntimeException{

    public ServiceException(Exception e) {
        super(e);
    }
}
