package io.soos.integration.domain.scan;

import java.util.LinkedHashMap;
import java.util.StringJoiner;

public class ScanResponse {
    private String projectId;
    private String scanId;
    private String scanUrl;
    private String scanStatusUrl;
    private LinkedHashMap originalResponse;


    public ScanResponse(LinkedHashMap<String, String> soosResponse) {
        String projectId = soosResponse.get("projectHash");
        String scanId = soosResponse.getOrDefault("ScanId", soosResponse.get("analysisId"));
        String scanUrl = soosResponse.get("scanUrl");
        String scanStatusUrl = soosResponse.get("scanStatusUrl");
        this.projectId = projectId;
        this.scanId = scanId;
        this.scanUrl = scanUrl;
        this.scanStatusUrl = scanStatusUrl;
        this.originalResponse = soosResponse;

    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getScanId() {
        return scanId;
    }

    public void setScanId(String scanId) {
        this.scanId = scanId;
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

    public LinkedHashMap getOriginalResponse() {
        return originalResponse;
    }

    public void setOriginalResponse(LinkedHashMap originalResponse) {
        this.originalResponse = originalResponse;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ScanResponse.class.getSimpleName() + "[", "]")
                .add("projectId='" + projectId + "'")
                .add("scanId='" + scanId + "'")
                .add("scanUrl='" + scanUrl + "'")
                .add("scanStatusUrl='" + scanStatusUrl + "'")
                .add("originalResponse=" + originalResponse)
                .toString();
    }
}
