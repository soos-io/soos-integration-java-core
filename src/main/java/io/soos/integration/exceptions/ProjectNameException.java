package io.soos.integration.exceptions;

public class ProjectNameException extends Exception {
    public ProjectNameException() {
    }

    public ProjectNameException(String message) {
        super(message);
    }

    public ProjectNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectNameException(Throwable cause) {
        super(cause);
    }

    public ProjectNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
