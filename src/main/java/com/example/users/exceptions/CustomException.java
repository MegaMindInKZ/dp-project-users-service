package com.example.users.exceptions;

public interface CustomException {
    int getHTTPStatus();
    Object getCaution();
    boolean isLoggingError();
}
