package com.example.users.exceptions;

public class ServiceException extends Exception{

    private final int code;

    public ServiceException(int code,String message)
    {
        super(message);
        this.code=code;
    }

    public int getCode() {
        return code;
    }

}
