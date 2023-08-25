package com.example.users.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

public class BadRequestException extends CustomException {
    private final int HTTPStatus = HttpServletResponse.SC_BAD_REQUEST;
    private Object errorObject;

    public BadRequestException(Object errorObject) {
        this.errorObject = errorObject;
    }

    @Override
    public int getHTTPStatus() {
        return HTTPStatus;
    }

    @Override
    public Object getCaution() {
        return errorObject;
    }
}
