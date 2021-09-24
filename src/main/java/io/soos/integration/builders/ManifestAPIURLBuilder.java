package io.soos.integration.builders;

import io.soos.integration.commons.Constants;

public class ManifestAPIURLBuilder extends APIURLBuilder implements IAPIURLBuilder<ManifestAPIURLBuilder> {
    protected String projectId;
    protected String analysisId;
    protected String manifestLabel;
    protected String manifestName;
    protected String targetVersion;

    public ManifestAPIURLBuilder(String baseURL, String clientId, String projectId, String analysisId, String manifestLabel, String manifestName, String targetVersion) {
        super(baseURL, clientId);
        this.projectId = projectId;
        this.analysisId = analysisId;
        this.manifestLabel = manifestLabel;
        this.manifestName = manifestName;
        this.targetVersion = targetVersion;
    }

    public ManifestAPIURLBuilder baseURL(String baseURL) {
        super.setBaseURL(baseURL);
        return this;
    }

    public ManifestAPIURLBuilder clientId(String clientId) {
        super.setClientId(clientId);
        return this;
    }

    public ManifestAPIURLBuilder projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public ManifestAPIURLBuilder analysisId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public ManifestAPIURLBuilder manifestLabel(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public ManifestAPIURLBuilder manifestName(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public ManifestAPIURLBuilder targetVersion(String clientId) {
        this.clientId = clientId;
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
                .append(Constants.URL_SLASH)
                .append(Constants.URL_MANIFESTS_PATH)
                .append(this.manifestLabel)
                .append(Constants.URL_SLASH)
                .append(this.manifestName)
                .append(Constants.URL_SLASH)
                .append(this.targetVersion)
                .toString();
    }
}
