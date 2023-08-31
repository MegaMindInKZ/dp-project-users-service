package com.example.users.utils.exceptions;

import com.example.users.utils.exceptions.caution.CautionableBehaviour;
import com.example.users.utils.exceptions.log.NotLoggable;
import jakarta.servlet.http.HttpServletResponse;

public class BadRequestException extends CustomException {

    public BadRequestException(Object errorObject) {
        super.HTTPStatus = HttpServletResponse.SC_BAD_REQUEST;
        super.loggableBehaviour = new NotLoggable();
        super.cautionBehaviour = new CautionableBehaviour(errorObject);
    }
}
