package io.soos.integration.builders;

import io.soos.integration.commons.Constants;

public class ManifestURIBuilder extends SOOSURIBuilder implements ISOOSURIBuilder<ManifestURIBuilder> {
    protected String projectId;
    protected String analysisId;
    protected String manifestLabel;
    protected String manifestName;
    protected String targetVersion;

    public ManifestURIBuilder() {
        super();
    }

    public ManifestURIBuilder(String baseURI, String clientId, String projectId, String analysisId, String manifestLabel, String manifestName, String targetVersion) {
        super(baseURI, clientId);
        this.projectId = projectId;
        this.analysisId = analysisId;
        this.manifestLabel = manifestLabel;
        this.manifestName = manifestName;
        this.targetVersion = targetVersion;
    }

    public ManifestURIBuilder baseURI(String baseURI) {
        super.setBaseURI(baseURI);
        return this;
    }

    public ManifestURIBuilder clientId(String clientId) {
        super.setClientId(clientId);
        return this;
    }

    public ManifestURIBuilder projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public ManifestURIBuilder analysisId(String analysisId) {
        this.analysisId = analysisId;
        return this;
    }

    public ManifestURIBuilder manifestLabel(String manifestLabel) {
        this.manifestLabel = manifestLabel;
        return this;
    }

    public ManifestURIBuilder manifestName(String manifestName) {
        this.manifestName = manifestName;
        return this;
    }

    public ManifestURIBuilder targetVersion(String targetVersion) {
        this.targetVersion = targetVersion;
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
                .append(Constants.URL_SLASH)
                .append(Constants.URL_MANIFESTS_PATH)
                .append(Constants.URL_SLASH)
                .append(this.manifestLabel)
                .append(Constants.URL_SLASH)
                .append(this.manifestName)
                .toString();
    }
}
