package io.soos.integration.domain.analysis;

import java.util.LinkedHashMap;

public class AnalysisResultResponse {
    protected String status;
    protected String analysisId;
    protected String reportUrl;
    protected int vulnerabilities;
    protected int violations;

    public AnalysisResultResponse(LinkedHashMap response) {
        if(response.containsKey("status")) {
            this.status = response.get("status").toString();
        }

        if(response.containsKey("analysisId")) {
            this.analysisId = response.get("analysisId").toString();
        }

        if(response.containsKey("result")) {
            LinkedHashMap result = (LinkedHashMap) response.get("result");

            if(result.containsKey("reportUrl")) {
                this.reportUrl = result.get("reportUrl").toString();
            }

            if(result.containsKey("vulnerabilities")) {
                this.vulnerabilities = Integer.parseInt(result.get("vulnerabilities").toString());
            }

            if(result.containsKey("violations")) {
                this.violations = Integer.parseInt(result.get("violations").toString());
            }
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public int getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(int vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    public int getViolations() {
        return violations;
    }

    public void setViolations(int violations) {
        this.violations = violations;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AnalysisResultResponse{");
        sb.append("status='").append(status).append('\'');
        sb.append(", analysisId='").append(analysisId).append('\'');
        sb.append(", reportUrl='").append(reportUrl).append('\'');
        sb.append(", vulnerabilities=").append(vulnerabilities);
        sb.append(", violations=").append(violations);
        sb.append('}');
        return sb.toString();
    }
}
