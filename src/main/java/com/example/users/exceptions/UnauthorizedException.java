package com.example.users.exceptions;

import com.example.users.exceptions.caution.NoCautionBehaviour;
import com.example.users.exceptions.log.NotLoggable;
import jakarta.servlet.http.HttpServletResponse;

public class UnauthorizedException extends CustomException{
    public UnauthorizedException() {
        super.HTTPStatus = HttpServletResponse.SC_UNAUTHORIZED;
        super.loggableBehaviour = new NotLoggable();
        super.cautionBehaviour = new NoCautionBehaviour();
    }
}
