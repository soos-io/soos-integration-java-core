package io.soos.integration.builders;

import io.soos.integration.commons.Constants;

public class CreateScanURIBuilder extends SOOSURIBuilder implements ISOOSURIBuilder<CreateScanURIBuilder> {

    protected String scanType;

    public CreateScanURIBuilder() {
    }

    public CreateScanURIBuilder(String baseURI, String clientId, String scanType) {
        super(baseURI, clientId);
        this.scanType = scanType;
    }

    @Override
    public CreateScanURIBuilder baseURI(String baseURI) {
        super.setBaseURI(baseURI);
        return this;
    }

    @Override
    public CreateScanURIBuilder clientId(String clientId) {
        super.setClientId(clientId);
        return this;
    }

    public CreateScanURIBuilder scanType(String scanType) {
        this.scanType = scanType;
        return this;
    }

    @Override
    public String buildURI() {
        StringBuilder uriBuilder = new StringBuilder();

        return uriBuilder.append(super.buildURI())
                .append(Constants.URL_SCAN_TYPES_PATH)
                .append(this.scanType)
                .append(Constants.URL_SLASH)
                .append(Constants.URL_SCANS_PATH)
                .toString();
    }
}
