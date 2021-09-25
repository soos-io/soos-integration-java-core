package io.soos.integration.builders;

import io.soos.integration.commons.Constants;

public class SOOSURIBuilder {
    protected String baseURI;
    protected String clientId;

    public SOOSURIBuilder() {
    }

    public SOOSURIBuilder(String baseURI, String clientId) {
        this.baseURI = baseURI;
        this.clientId = clientId;
    }

    protected void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    protected void setClientId(String clientId) {
        this.clientId = clientId;
    }

    protected String buildURI() {
        StringBuilder apiURLBuilder = new StringBuilder();
        return apiURLBuilder
                .append(this.baseURI)
                .append(Constants.URL_CLIENTS_PATH)
                .append(this.clientId)
                .append(Constants.URL_SLASH)
                .toString();
    }
}
