package io.soos.integration.domain;

import io.soos.integration.api.SOOSAPI;
import io.soos.integration.commons.Constants;
import io.soos.integration.domain.Mode;
import io.soos.integration.domain.OnFailure;
import io.soos.integration.exceptions.AnalysisFailureException;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SOOS {
    private final boolean verboseLoggingEnabled;
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

    private SOOSAPI api;

    public SOOS(String projectName,
                Mode mode,
                OnFailure onFailure,
                String sourceCodePath,
                String clientId,
                String apiKey,
                String directoriesToExclude,
                String filesToExclude,
                int analysisResultMaxWait,
                int analysisResultPollingInterval,
                String baseUri,
                String workingDirectory,
                String branchUri,
                String branchName,
                String commitHash,
                String buildUri,
                String buildVersion,
                String operatingEnvironment,
                String integrationName,
                boolean verboseLoggingEnabled) {
        this.projectName = projectName;
        this.mode = mode;
        this.onFailure = onFailure;
        this.sourceCodePath = sourceCodePath;
        this.clientId = clientId;
        this.apiKey = apiKey;
        this.directoriesToExclude = directoriesToExclude;
        this.filesToExclude = filesToExclude;
        this.analysisResultMaxWait = analysisResultMaxWait;
        this.analysisResultPollingInterval = analysisResultPollingInterval;
        this.baseUri = baseUri;
        this.workingDirectory = workingDirectory;
        this.branchUri = branchUri;
        this.branchName = branchName;
        this.commitHash = commitHash;
        this.buildUri = buildUri;
        this.buildVersion = buildVersion;
        this.operatingEnvironment = operatingEnvironment;
        this.integrationName = integrationName;
        this.verboseLoggingEnabled = verboseLoggingEnabled;

        if (StringUtils.isEmpty(this.operatingEnvironment)) {
            try {
                this.operatingEnvironment = System.getProperty("os.name");
            } catch (Exception ex) {
                this.operatingEnvironment = "";
            }
        }

        this.api = new SOOSAPI();
    }

    public SOOS(
            String projectName,
            Mode mode,
            OnFailure onFailure,
            String sourceCodePath,
            String clientId,
            String apiKey,
            String directoriesToExclude,
            String filesToExclude,
            int analysisResultMaxWait,
            int analysisResultPollingInterval,
            String baseUri,
            String workingDirectory,
            boolean verboseLoggingEnabled) {
        this(projectName, mode, onFailure, sourceCodePath, clientId, apiKey,
                directoriesToExclude, filesToExclude,
                analysisResultMaxWait, analysisResultPollingInterval,
                baseUri, workingDirectory,
                "", // branchUri,
                "", // branchName,
                "", // commitHash,
                "", // buildUri,
                "", // buildVersion,
                "", // operatingEnvironment,
                "",  //integrationName,
                verboseLoggingEnabled
        );
    }

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

    public boolean isVerboseLoggingEnabled() {
        return verboseLoggingEnabled;
    }

    public SOOSStructureAPIBody getSOOSStructureAPIBody() {
        String pattern = "MM/dd/yyyy, hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String name = simpleDateFormat.format(new Date());
        SOOSStructureAPIBody body = new SOOSStructureAPIBody(this.projectName, name);

        if(StringUtils.isNoneEmpty(this.branchUri)) {
            body.setBranchUri(this.branchUri);
        }

        if(StringUtils.isNoneEmpty(this.branchName)) {
            body.setBranch(this.branchName);
        }

        if(StringUtils.isNoneEmpty(this.commitHash)) {
            body.setCommitHash(this.commitHash);
        }

        if(StringUtils.isNoneEmpty(this.buildVersion)) {
            body.setBuildVersion(this.buildVersion);
        }

        if(StringUtils.isNoneEmpty(this.buildUri)) {
            body.setBuildUri(this.buildUri);
        }

        if(StringUtils.isNoneEmpty(this.operatingEnvironment)) {
            body.setOperatingEnvironment(this.operatingEnvironment);
        }

        if(StringUtils.isNoneEmpty(this.integrationName)) {
            body.setIntegrationName(this.integrationName);
        }

        return body;
    }


}
