package com.example.users.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class ForbiddenException extends RuntimeException implements CustomException{
    private final int HTTPStatus = HttpServletResponse.SC_FORBIDDEN;

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
