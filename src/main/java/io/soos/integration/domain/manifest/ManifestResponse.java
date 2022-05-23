package io.soos.integration.domain.manifest;

import java.util.LinkedHashMap;

public class ManifestResponse {
    protected String projectId;
    protected String analysisId;

    public ManifestResponse(LinkedHashMap response) {
        if(response.containsKey("projectId")) {
            this.projectId = response.get("projectId").toString();
        }
        if(response.containsKey("analysisId")) {
            this.analysisId = response.get("analysisId").toString();
        }
    }

    public String getProjectId() {
        return projectId;
    }

    public String getAnalysisId() {
        return analysisId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ManifestResponse{");
        sb.append("projectId='").append(projectId).append('\'');
        sb.append(", analysisId='").append(analysisId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
