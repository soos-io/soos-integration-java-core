package io.soos.integration.builders;

import io.soos.integration.commons.Constants;

public class StructureAPIURLBuilder extends APIURLBuilder implements IAPIURLBuilder<StructureAPIURLBuilder> {

    public StructureAPIURLBuilder() {
    }

    public StructureAPIURLBuilder(String baseURL, String clientId) {
        super(baseURL, clientId);
    }

    @Override
    public StructureAPIURLBuilder baseURL(String baseURL) {
        super.setBaseURL(baseURL);
        return this;
    }

    @Override
    public StructureAPIURLBuilder clientId(String clientId) {
        super.setClientId(clientId);
        return this;
    }

    @Override
    public String buildURL() {
        StringBuilder urlBuilder = new StringBuilder();

        return urlBuilder.append(super.buildURL())
                .append(Constants.URL_ANALYSIS_PATH)
                .append(Constants.URL_STRUCTURE_PATH)
                .toString();
    }
}
