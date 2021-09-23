package io.soos.integration.commons;

public interface ILogger {
    void log(String message);
    void error(String message);
    void warn(String message);
}
