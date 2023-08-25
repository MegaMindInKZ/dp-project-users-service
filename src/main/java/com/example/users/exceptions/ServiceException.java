package com.example.users.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class ServiceException extends RuntimeException implements CustomException{
    private final int HTTPStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    public ServiceException(Exception e) {
        super(e);
    }
    @Override
    public int getHTTPStatus() {
        return HTTPStatus;
    }

    @Override
    public Object getCaution() {
        return null;
    }

    @Override
    public boolean isLoggingError() {
        return true;
    }
}
