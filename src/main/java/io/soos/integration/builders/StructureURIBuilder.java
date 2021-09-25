package io.soos.integration.builders;

import io.soos.integration.commons.Constants;

public class StructureURIBuilder extends SOOSURIBuilder implements ISOOSURIBuilder<StructureURIBuilder> {

    public StructureURIBuilder() {
    }

    public StructureURIBuilder(String baseURI, String clientId) {
        super(baseURI, clientId);
    }

    @Override
    public StructureURIBuilder baseURI(String baseURI) {
        super.setBaseURI(baseURI);
        return this;
    }

    @Override
    public StructureURIBuilder clientId(String clientId) {
        super.setClientId(clientId);
        return this;
    }

    @Override
    public String buildURI() {
        StringBuilder uriBuilder = new StringBuilder();

        return uriBuilder.append(super.buildURI())
                .append(Constants.URL_ANALYSIS_PATH)
                .append(Constants.URL_STRUCTURE_PATH)
                .toString();
    }
}
