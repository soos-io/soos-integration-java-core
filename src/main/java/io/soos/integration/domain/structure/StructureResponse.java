package io.soos.integration.domain.structure;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.LinkedHashMap;

public class StructureResponse {
    private String structureId;
    private String projectId;
    private String analysisId;
    private String reportURL;
    private String embedUrl;
    private String reportStatusUrl;
    private LinkedHashMap originalResponse;

    public StructureResponse() {
    }

    public StructureResponse(LinkedHashMap<String, String> soosResponse) {
        this(soosResponse.get("Id"),
                soosResponse.get("projectId"),
                soosResponse.get("Id"),
                soosResponse.get("reportUrl"),
                soosResponse.get("embedUrl"),
                soosResponse.get("reportStatusUrl"));
        this.originalResponse = soosResponse;

    }

    public StructureResponse(String structureId, String projectId, String analysisId, String reportURL, String embedUrl, String reportStatusUrl) {
        this.structureId = structureId;
        this.projectId = projectId;
        this.analysisId = analysisId;
        this.reportURL = reportURL;
        this.embedUrl = embedUrl;
        this.reportStatusUrl = reportStatusUrl;
    }

    public String getStructureId() {
        return structureId;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }

    public String getReportURL() {
        return reportURL;
    }

    public void setReportURL(String reportURL) {
        this.reportURL = reportURL;
    }

    public String getEmbedUrl() {
        return embedUrl;
    }

    public void setEmbedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
    }

    public String getReportStatusUrl() {
        return reportStatusUrl;
    }

    public void setReportStatusUrl(String reportStatusUrl) {
        this.reportStatusUrl = reportStatusUrl;
    }

    public LinkedHashMap getOriginalResponse() {
        return originalResponse;
    }

    public void setOriginalResponse(LinkedHashMap originalResponse) {
        this.originalResponse = originalResponse;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StructureResponse{");
        sb.append("structureId='").append(structureId).append('\'');
        sb.append(", projectId='").append(projectId).append('\'');
        sb.append(", analysisId='").append(analysisId).append('\'');
        sb.append(", reportURL='").append(reportURL).append('\'');
        sb.append(", embedUrl='").append(embedUrl).append('\'');
        sb.append(", reportStatusUrl='").append(reportStatusUrl).append('\'');
        sb.append(", originalResponse=").append(originalResponse);
        sb.append('}');
        return sb.toString();
    }
}
