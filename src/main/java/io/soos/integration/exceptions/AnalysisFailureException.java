package io.soos.integration.exceptions;

public class AnalysisFailureException extends Exception {
    public AnalysisFailureException() {
    }

    public AnalysisFailureException(String message) {
        super(message);
    }

    public AnalysisFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnalysisFailureException(Throwable cause) {
        super(cause);
    }

    public AnalysisFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
