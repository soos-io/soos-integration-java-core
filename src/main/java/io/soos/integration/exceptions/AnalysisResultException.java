package io.soos.integration.exceptions;

public class AnalysisResultException extends Exception {
    public AnalysisResultException() {
    }

    public AnalysisResultException(String message) {
        super(message);
    }

    public AnalysisResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnalysisResultException(Throwable cause) {
        super(cause);
    }

    public AnalysisResultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
