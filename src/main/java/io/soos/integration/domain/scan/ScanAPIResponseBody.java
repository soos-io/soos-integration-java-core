package io.soos.integration.domain.scan;

import io.soos.integration.domain.ErrorResponse;

public class ScanAPIResponseBody {
    protected String clientHash;
    protected String projectHash;
    protected String branchHash;
    protected String scanId;
    protected String analysisId;
    protected String scanType;
    protected String scanUrl;
    protected String scanStatusUrl;
    protected ErrorResponse errors;

    public ScanAPIResponseBody(String clientHash, String projectHash, String branchHash, String scanId, String analysisId, String scanType, String scanUrl, String scanStatusUrl) {
        this.clientHash = clientHash;
        this.projectHash = projectHash;
        this.branchHash = branchHash;
        this.scanId = scanId;
        this.analysisId = analysisId;
        this.scanType = scanType;
        this.scanUrl = scanUrl;
        this.scanStatusUrl = scanStatusUrl;
    }

    public ScanAPIResponseBody(String clientHash, String projectHash, String branchHash, String scanId, String analysisId, String scanType, String scanUrl, String scanStatusUrl, ErrorResponse errors) {
        this.clientHash = clientHash;
        this.projectHash = projectHash;
        this.branchHash = branchHash;
        this.scanId = scanId;
        this.analysisId = analysisId;
        this.scanType = scanType;
        this.scanUrl = scanUrl;
        this.scanStatusUrl = scanStatusUrl;
        this.errors = errors;
    }

    public ScanAPIResponseBody(String clientHash, String projectHash, String branchHash, String analysisId, String scanType, String scanUrl, String scanStatusUrl) {
        this.clientHash = clientHash;
        this.projectHash = projectHash;
        this.branchHash = branchHash;
        this.analysisId = analysisId;
        this.scanType = scanType;
        this.scanUrl = scanUrl;
        this.scanStatusUrl = scanStatusUrl;
    }

    public String getClientHash() {
        return clientHash;
    }

    public void setClientHash(String clientHash) {
        this.clientHash = clientHash;
    }

    public String getProjectHash() {
        return projectHash;
    }

    public void setProjectHash(String projectHash) {
        this.projectHash = projectHash;
    }

    public String getBranchHash() {
        return branchHash;
    }

    public void setBranchHash(String branchHash) {
        this.branchHash = branchHash;
    }

    public String getScanId() {
        return scanId;
    }

    public void setScanId(String scanId) {
        this.scanId = scanId;
    }

    public String getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }

    public String getScanType() {
        return scanType;
    }

    public void setScanType(String scanType) {
        this.scanType = scanType;
    }

    public String getScanUrl() {
        return scanUrl;
    }

    public void setScanUrl(String scanUrl) {
        this.scanUrl = scanUrl;
    }

    public String getScanStatusUrl() {
        return scanStatusUrl;
    }

    public void setScanStatusUrl(String scanStatusUrl) {
        this.scanStatusUrl = scanStatusUrl;
    }

    public ErrorResponse getErrors() {
        return errors;
    }

    public void setErrors(ErrorResponse errors) {
        this.errors = errors;
    }
}
