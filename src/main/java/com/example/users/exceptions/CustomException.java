package com.example.users.exceptions;

public abstract class CustomException extends RuntimeException{
    public abstract int getHTTPStatus();
    public Object getCaution(){
        return null;
    }
}
