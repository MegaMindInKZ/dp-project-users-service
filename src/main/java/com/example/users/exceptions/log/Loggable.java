package com.example.users.exceptions.log;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Loggable implements LoggableBehaviour{
    Object loggingMessage;
    public Loggable(Object object){
        this.loggingMessage = object;
    }
    @Override
    public void logMessage(OutputStream outputStream) {
        try {
            if(loggingMessage instanceof Exception){
                Exception e = (Exception) loggingMessage;
                PrintWriter printWriter = new PrintWriter(outputStream);
                try
                {
                    e.printStackTrace(printWriter);
                } finally
                {
                    printWriter.close();
                }
                return;
            }
            outputStream.write(loggingMessage.toString().getBytes());
        } catch (IOException e) {
        }
    }
}
