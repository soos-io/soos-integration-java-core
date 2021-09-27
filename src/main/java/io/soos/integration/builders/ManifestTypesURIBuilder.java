package io.soos.integration.builders;

import io.soos.integration.commons.Constants;

public class ManifestTypesURIBuilder extends SOOSURIBuilder implements ISOOSURIBuilder<ManifestTypesURIBuilder> {

    public ManifestTypesURIBuilder() {
    }

    public ManifestTypesURIBuilder(String baseURL, String clientId) {
        super(baseURL, clientId);
    }

    @Override
    public ManifestTypesURIBuilder baseURI(String baseURI) {
        super.setBaseURI(baseURI);
        return this;
    }

    @Override
    public ManifestTypesURIBuilder clientId(String clientId) {
        super.setClientId(clientId);
        return this;
    }

    @Override
    public String buildURI() {
        StringBuilder uriBuilder = new StringBuilder();

        return uriBuilder.append(super.buildURI())
                .append(Constants.URL_MANIFESTS_PATH)
                .toString();
    }
}
