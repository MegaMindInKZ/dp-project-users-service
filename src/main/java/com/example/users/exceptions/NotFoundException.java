package com.example.users.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class NotFoundException extends RuntimeException implements CustomException{
    private final int HTTPStatus = HttpServletResponse.SC_NOT_FOUND;

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
        return false;
    }
}
