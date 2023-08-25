package com.example.users.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class NotFoundException extends CustomException{
    private final int HTTPStatus = HttpServletResponse.SC_NOT_FOUND;

    @Override
    public int getHTTPStatus() {
        return HTTPStatus;
    }
}
