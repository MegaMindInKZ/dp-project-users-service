package com.example.users.exceptions;

import com.example.users.exceptions.caution.NoCautionBehaviour;
import com.example.users.exceptions.log.NotLoggable;
import jakarta.servlet.http.HttpServletResponse;

public class NotFoundException extends CustomException{
    public NotFoundException() {
        super.HTTPStatus = HttpServletResponse.SC_NOT_FOUND;
        super.loggableBehaviour = new NotLoggable();
        super.cautionBehaviour = new NoCautionBehaviour();
    }
}
