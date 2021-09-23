package io.soos.integration.domain;

public enum Mode {
    RUN_AND_WAIT("run_and_wait"), ASYNC_INIT("async_init"), ASYNC_RESULT("async_result");

    private String mode;

    Mode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
