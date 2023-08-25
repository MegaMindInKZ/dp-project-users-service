package com.example.users.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class PaymentRequiredException extends CustomException{
    private final int HTTPStatus = HttpServletResponse.SC_PAYMENT_REQUIRED;

    @Override
    public int getHTTPStatus() {
        return HTTPStatus;
    }
}
