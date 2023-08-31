package com.example.users.utils.exceptions;

import com.example.users.utils.exceptions.caution.NoCautionBehaviour;
import com.example.users.utils.exceptions.log.NotLoggable;
import jakarta.servlet.http.HttpServletResponse;

public class UnauthorizedException extends CustomException{
    public UnauthorizedException() {
        super.HTTPStatus = HttpServletResponse.SC_UNAUTHORIZED;
        super.loggableBehaviour = new NotLoggable();
        super.cautionBehaviour = new NoCautionBehaviour();
    }
}
