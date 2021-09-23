package io.soos.integration.domain;

public enum OnFailure {
    FAIL_THE_BUILD("fail_the_build"), CONTINUE_ON_FAILURE("continue_on_failure");


    private String mode;

    OnFailure(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
