package com.example.users.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class ForbiddenException extends CustomException{
    private final int HTTPStatus = HttpServletResponse.SC_FORBIDDEN;

    @Override
    public int getHTTPStatus() {
        return HTTPStatus;
    }
}
