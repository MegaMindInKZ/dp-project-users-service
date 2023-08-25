package com.example.users.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class PaymentRequiredException extends RuntimeException implements CustomException{
    private final int HTTPStatus = HttpServletResponse.SC_PAYMENT_REQUIRED;

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
