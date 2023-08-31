package com.example.users.utils.exceptions;

import com.example.users.utils.exceptions.caution.NoCautionBehaviour;
import com.example.users.utils.exceptions.log.Loggable;
import jakarta.servlet.http.HttpServletResponse;

public class ServiceException extends CustomException {
    public ServiceException(Exception e) {
        super.loggableBehaviour = new Loggable(e);
        super.HTTPStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        super.cautionBehaviour = new NoCautionBehaviour();
    }

}
