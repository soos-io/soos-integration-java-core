package io.soos.integration.domain.structure;

public class StructureAPIResponseBody {
    protected String Id;
    protected String projectId;
    protected String reportUrl;
    protected String embedUrl;
    protected String reportStatusUrl;

    public StructureAPIResponseBody(String id, String projectId, String reportUrl, String embedUrl, String reportStatusUrl) {
        this.Id = id;
        this.projectId = projectId;
        this.reportUrl = reportUrl;
        this.embedUrl = embedUrl;
        this.reportStatusUrl = reportStatusUrl;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
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
}
