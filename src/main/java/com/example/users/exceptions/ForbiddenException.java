package com.example.users.exceptions;

import com.example.users.exceptions.log.NotLoggable;
import jakarta.servlet.http.HttpServletResponse;

public class ForbiddenException extends CustomException{
    public ForbiddenException() {
        super.HTTPStatus = HttpServletResponse.SC_FORBIDDEN;
        super.loggableBehaviour = new NotLoggable();
    }
}
