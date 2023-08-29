package com.example.users.exceptions;

import com.example.users.exceptions.caution.CautionableBehaviour;
import com.example.users.exceptions.caution.NoCautionBehaviour;
import com.example.users.exceptions.log.Loggable;
import com.example.users.exceptions.log.LoggableBehaviour;
import com.example.users.exceptions.log.NotLoggable;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

public class BadRequestException extends CustomException {

    public BadRequestException(Object errorObject) {
        super.HTTPStatus = HttpServletResponse.SC_BAD_REQUEST;
        super.loggableBehaviour = new NotLoggable();
        super.cautionBehaviour = new CautionableBehaviour(errorObject);
    }
}
