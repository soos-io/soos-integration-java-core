package io.soos.integration;

import io.soos.integration.domain.*;

public class SOOS {
    private String projectName;
    private Mode mode;
    private OnFailure onFailure;
    private String sourceCodePath;
    private String clientId;
    private String apiKey;
    private String directoriesToExclude;
    private String filesToExclude;
    private int analysisResultMaxWait;
    private int analysisResultPollingInterval;
    private String baseUri;
    private String workingDirectory;

    private String branchUri;
    private String branchName;
    private String commitHash;
    private String buildUri;
    private String buildVersion;
    private String operatingEnvironment;
    private String integrationName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public OnFailure getOnFailure() {
        return onFailure;
    }

    public void setOnFailure(OnFailure onFailure) {
        this.onFailure = onFailure;
    }

    public String getSourceCodePath() {
        return sourceCodePath;
    }

    public void setSourceCodePath(String sourceCodePath) {
        this.sourceCodePath = sourceCodePath;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getDirectoriesToExclude() {
        return directoriesToExclude;
    }

    public void setDirectoriesToExclude(String directoriesToExclude) {
        this.directoriesToExclude = directoriesToExclude;
    }

    public String getFilesToExclude() {
        return filesToExclude;
    }

    public void setFilesToExclude(String filesToExclude) {
        this.filesToExclude = filesToExclude;
    }

    public int getAnalysisResultMaxWait() {
        return analysisResultMaxWait;
    }

    public void setAnalysisResultMaxWait(int analysisResultMaxWait) {
        this.analysisResultMaxWait = analysisResultMaxWait;
    }

    public int getAnalysisResultPollingInterval() {
        return analysisResultPollingInterval;
    }

    public void setAnalysisResultPollingInterval(int analysisResultPollingInterval) {
        this.analysisResultPollingInterval = analysisResultPollingInterval;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public String getBranchUri() {
        return branchUri;
    }

    public void setBranchUri(String branchUri) {
        this.branchUri = branchUri;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public String getBuildUri() {
        return buildUri;
    }

    public void setBuildUri(String buildUri) {
        this.buildUri = buildUri;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getOperatingEnvironment() {
        return operatingEnvironment;
    }

    public void setOperatingEnvironment(String operatingEnvironment) {
        this.operatingEnvironment = operatingEnvironment;
    }

    public String getIntegrationName() {
        return integrationName;
    }

    public void setIntegrationName(String integrationName) {
        this.integrationName = integrationName;
    }
}
