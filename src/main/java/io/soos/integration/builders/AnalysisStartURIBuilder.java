package io.soos.integration.builders;

import io.soos.integration.commons.Constants;

public class AnalysisStartURIBuilder extends SOOSURIBuilder implements ISOOSURIBuilder<AnalysisStartURIBuilder> {

    protected String projectId;
    protected String analysisId;

    public AnalysisStartURIBuilder() {
    }

    public AnalysisStartURIBuilder(String baseURI, String clientId, String projectId, String analysisId) {
        super(baseURI, clientId);
        this.projectId = projectId;
        this.analysisId = analysisId;
    }

    @Override
    public AnalysisStartURIBuilder baseURI(String baseURI) {
        super.setBaseURI(baseURI);
        return this;
    }

    @Override
    public AnalysisStartURIBuilder clientId(String clientId) {
        super.setClientId(clientId);
        return this;
    }

    public AnalysisStartURIBuilder projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public AnalysisStartURIBuilder analysisId(String analysisId) {
        this.analysisId = analysisId;
        return this;
    }

    @Override
    public String buildURI() {
        StringBuilder uriBuilder = new StringBuilder();

        return uriBuilder.append(super.buildURI())
                .append(Constants.URL_PROJECTS_PATH)
                .append(this.projectId)
                .append(Constants.URL_SLASH)
                .append(Constants.URL_ANALYSIS_PATH)
                .append(this.analysisId)
                .toString();
    }
}
