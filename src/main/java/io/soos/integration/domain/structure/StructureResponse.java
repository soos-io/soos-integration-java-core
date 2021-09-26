package io.soos.integration.domain.structure;

public class StructureResponse {
    private String structureId;
    private String projectId;
    private String analysisId;
    private String reportURL;
    private String embedUrl;
    private String reportStatusUrl;
    private StructureAPIResponseBody originalResponse;

    public StructureResponse() {
    }

    public StructureResponse(StructureAPIResponseBody soosResponse) {
        this(soosResponse.getId(),
                soosResponse.getProjectId(),
                soosResponse.getId(),
                soosResponse.getReportUrl(),
                soosResponse.getEmbedUrl(),
                soosResponse.getReportStatusUrl());
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

    public StructureAPIResponseBody getOriginalResponse() {
        return originalResponse;
    }

    public void setOriginalResponse(StructureAPIResponseBody originalResponse) {
        this.originalResponse = originalResponse;
    }
}
