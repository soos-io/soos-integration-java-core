package io.soos.integration.builders;

import io.soos.integration.commons.Constants;

public class ManifestTypesURLBuilder extends SOOSURIBuilder implements ISOOSURIBuilder<ManifestTypesURLBuilder> {

    public ManifestTypesURLBuilder() {
    }

    public ManifestTypesURLBuilder(String baseURL, String clientId) {
        super(baseURL, clientId);
    }

    @Override
    public ManifestTypesURLBuilder baseURI(String baseURI) {
        super.setBaseURI(baseURI);
        return this;
    }

    @Override
    public ManifestTypesURLBuilder clientId(String clientId) {
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
