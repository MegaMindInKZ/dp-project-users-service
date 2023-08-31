package com.example.users.utils.exceptions.log;

import java.io.OutputStream;

public class NotLoggable implements LoggableBehaviour{

    @Override
    public void logMessage(OutputStream outputStream) {

    }
}
