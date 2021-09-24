package io.soos.integration.builders;

import io.soos.integration.commons.Constants;

public class APIURLBuilder {
    protected String baseURL;
    protected String clientId;

    public APIURLBuilder() {
    }

    public APIURLBuilder(String baseURL, String clientId) {
        this.baseURL = baseURL;
        this.clientId = clientId;
    }

    protected void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    protected void setClientId(String clientId) {
        this.clientId = clientId;
    }

    protected String buildURL() {
        StringBuilder apiURLBuilder = new StringBuilder();
        return apiURLBuilder
                .append(this.baseURL)
                .append(Constants.URL_CLIENTS_PATH)
                .append(this.clientId)
                .append(Constants.URL_SLASH)
                .toString();
    }
}
