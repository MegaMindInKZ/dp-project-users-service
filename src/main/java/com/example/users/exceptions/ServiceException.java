package com.example.users.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class ServiceException extends CustomException implements LoggableCustomException{
    private final int HTTPStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    Exception e;
    public ServiceException(Exception e) {
        this.e = e;
    }
    @Override
    public int getHTTPStatus() {
        return HTTPStatus;
    }

    @Override
    public Object loggingMessage() {
        return e.getStackTrace();
    }
}
