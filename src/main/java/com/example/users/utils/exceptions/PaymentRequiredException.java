package com.example.users.utils.exceptions;

import com.example.users.utils.exceptions.caution.NoCautionBehaviour;
import com.example.users.utils.exceptions.log.NotLoggable;
import jakarta.servlet.http.HttpServletResponse;

public class PaymentRequiredException extends CustomException{
    public PaymentRequiredException() {
        super.HTTPStatus = HttpServletResponse.SC_PAYMENT_REQUIRED;
        super.loggableBehaviour = new NotLoggable();
        super.cautionBehaviour = new NoCautionBehaviour();
    }
}
