package com.example.users.exceptions.log;

import java.io.OutputStream;

public class NotLoggable implements LoggableBehaviour{

    @Override
    public void logMessage(OutputStream outputStream) {

    }

    @Override
    public boolean isLoggable() {
        return false;
    }
}
