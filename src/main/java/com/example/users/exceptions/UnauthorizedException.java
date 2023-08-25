package com.example.users.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class UnauthorizedException extends CustomException{
    private final int HTTPStatus = HttpServletResponse.SC_UNAUTHORIZED;

    @Override
    public int getHTTPStatus() {
        return HTTPStatus;
    }
}
