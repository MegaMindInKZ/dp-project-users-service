package com.example.users.exceptions.log;

import java.io.OutputStream;

public interface LoggableBehaviour {
    void logMessage(OutputStream outputStream);
    boolean isLoggable();
}
