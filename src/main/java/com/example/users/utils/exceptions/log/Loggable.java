package com.example.users.utils.exceptions.log;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Loggable implements LoggableBehaviour{
    Object loggingMessage;
    public Loggable(Object object){
        this.loggingMessage = object;
    }

    @Override
    public Object getLog() {
        return loggingMessage;
    }
}
