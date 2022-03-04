package io.soos.integration.domain.analysis;

import java.util.LinkedHashMap;
import org.apache.commons.lang3.ObjectUtils;

public class AnalysisResultResponse {
    protected String status;
    protected int vulnerabilities;
    protected int violations;
    protected String clientHash;
    protected String projectHash;
    protected String branchHash;
    protected String scanId;
    protected String analysisId;
    protected String scanType;
    protected String scanUrl;
    protected String scanSarifUrl;
    protected String scanStatusUrl;
    protected String errors;


    public AnalysisResultResponse(LinkedHashMap response) throws Exception {
        if(response.containsKey("status")) {
            this.status = response.get("status").toString();
        }

        if(response.containsKey("analysisId")) {
            this.analysisId = response.get("analysisId").toString();
        }

        if(response.containsKey("scanStatusUrl")) {
            this.scanStatusUrl = response.get("scanStatusUrl").toString();
        }

        if(response.containsKey("scanUrl")) {
            this.scanUrl = response.get("scanUrl").toString();
        }

        if(response.containsKey("vulnerabilities")) {
            this.vulnerabilities = Integer.parseInt(response.get("vulnerabilities").toString().replaceAll("[^0-9]", ""));
        }

        if(response.containsKey("violations")) {
            this.violations = Integer.parseInt(response.get("violations").toString().replaceAll("[^0-9]", ""));
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

    public String getClientHash() { return clientHash; }

    public void setClientHash(String clientHash) { this.clientHash = clientHash; }

    public String getProjectHash() { return projectHash; }

    public void setProjectHash(String projectHash) { this.projectHash = projectHash; }

    public String getBranchHash() { return branchHash; }

    public void setBranchHash(String branchHash) { this.branchHash = branchHash; }

    public String getScanId() { return scanId; }

    public void setScanId(String scanId) { this.scanId = scanId; }

    public String getScanType() { return scanType; }

    public void setScanType(String scanType) { this.scanType = scanType; }

    public String getScanUrl() { return scanUrl; }

    public void setScanUrl(String scanUrl) { this.scanUrl = scanUrl; }

    public String getScanSarifUrl() { return scanSarifUrl; }

    public void setScanSarifUrl(String scanSarifUrl) { this.scanSarifUrl = scanSarifUrl; }

    public String getScanStatusUrl() { return scanStatusUrl; }

    public void setScanStatusUrl(String scanStatusUrl) { this.scanStatusUrl = scanStatusUrl; }

    public String getErrors() { return errors; }

    public void setErrors(String errors) { this.errors = errors; }

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
        sb.append(", scanStatusUrl='").append(scanStatusUrl).append('\'');
        sb.append(", vulnerabilities=").append(vulnerabilities);
        sb.append(", violations=").append(violations);
        sb.append('}');
        return sb.toString();
    }
}
