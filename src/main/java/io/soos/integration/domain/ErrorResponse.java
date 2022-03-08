package io.soos.integration.domain;

import java.util.LinkedHashMap;

public class ErrorResponse {
    protected String code;
    protected String message;
    protected LinkedHashMap data;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse(String code, String message, LinkedHashMap data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LinkedHashMap getData() {
        return data;
    }

    public void setData(LinkedHashMap data) {
        this.data = data;
    }
}
