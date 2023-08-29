package com.example.users.exceptions.log;

import java.io.IOException;
import java.io.OutputStream;

public class Loggable implements LoggableBehaviour{
    Object loggingMessage;
    public Loggable(Object object){
        this.loggingMessage = object;
    }
    @Override
    public void logMessage(OutputStream outputStream) {
        try {
            outputStream.write(loggingMessage.toString().getBytes());
        } catch (IOException e) {
        }
    }

    @Override
    public boolean isLoggable() {
        return true;
    }
}
