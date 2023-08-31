package com.example.users.utils.exceptions;

import com.example.users.utils.exceptions.caution.CautionBehaviour;
import com.example.users.utils.exceptions.log.LoggableBehaviour;

import java.io.OutputStream;

public abstract class CustomException extends RuntimeException{
    protected int HTTPStatus;
    LoggableBehaviour loggableBehaviour;
    CautionBehaviour cautionBehaviour;
    public int getHTTPStatus(){
        return HTTPStatus;
    }
    public Object getCaution(){
        return cautionBehaviour.getCaution();
    }

    public void logMessage(OutputStream outputStream){
        loggableBehaviour.logMessage(outputStream);
    }

}
