package com.example.users.exceptions;

import com.example.users.exceptions.caution.NoCautionBehaviour;
import com.example.users.exceptions.log.Loggable;
import com.example.users.exceptions.log.LoggableBehaviour;
import jakarta.servlet.http.HttpServletResponse;

public class ServiceException extends CustomException {
    public ServiceException(Exception e) {
        super.loggableBehaviour = new Loggable(e);
        super.HTTPStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        super.cautionBehaviour = new NoCautionBehaviour();
    }

}
