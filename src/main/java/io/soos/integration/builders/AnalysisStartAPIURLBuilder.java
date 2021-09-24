package io.soos.integration.builders;

import io.soos.integration.commons.Constants;

public class AnalysisStartAPIURLBuilder extends APIURLBuilder implements IAPIURLBuilder<AnalysisStartAPIURLBuilder> {

    protected String projectId;
    protected String analysisId;

    public AnalysisStartAPIURLBuilder(String projectId, String analysisId) {
        this.projectId = projectId;
        this.analysisId = analysisId;
    }

    public AnalysisStartAPIURLBuilder(String baseURL, String clientId, String projectId, String analysisId) {
        super(baseURL, clientId);
        this.projectId = projectId;
        this.analysisId = analysisId;
    }

    @Override
    public AnalysisStartAPIURLBuilder baseURL(String baseURL) {
        super.setBaseURL(baseURL);
        return this;
    }

    @Override
    public AnalysisStartAPIURLBuilder clientId(String clientId) {
        super.setClientId(clientId);
        return this;
    }

    public AnalysisStartAPIURLBuilder projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public AnalysisStartAPIURLBuilder analysisId(String analysisId) {
        this.analysisId = analysisId;
        return this;
    }

    @Override
    public String buildURL() {
        StringBuilder urlBuilder = new StringBuilder();

        return urlBuilder.append(super.buildURL())
                .append(Constants.URL_PROJECTS_PATH)
                .append(this.projectId)
                .append(Constants.URL_SLASH)
                .append(Constants.URL_ANALYSIS_PATH)
                .append(this.analysisId)
                .toString();
    }
}
