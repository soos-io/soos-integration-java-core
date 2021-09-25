package io.soos.integration.domain;

import io.soos.integration.commons.Constants;
import io.soos.integration.validators.ContextValidator;
import org.apache.commons.lang3.StringUtils;

public class Context {
    protected String baseURI;
    protected String sourceCodePath;
    protected String projectName;
    protected String clientId;
    protected String apiKey;
    protected String commitHash;
    protected String branchName;
    protected String branchURI;
    protected String buildVersion;
    protected String buildURI;
    protected String operatingEnvironment;
    protected String integrationName;
    protected String integrationType;

    public Context() {
        this.integrationType = Constants.INTEGRATION_TYPE;
    }

    public String getBaseURI() {
        return baseURI;
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    public String getSourceCodePath() {
        return sourceCodePath;
    }

    public void setSourceCodePath(String sourceCodePath) {
        this.sourceCodePath = sourceCodePath;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchURI() {
        return branchURI;
    }

    public void setBranchURI(String branchURI) {
        this.branchURI = branchURI;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getBuildURI() {
        return buildURI;
    }

    public void setBuildURI(String buildURI) {
        this.buildURI = buildURI;
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

    public String getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }

    private void reset() {
        this.baseURI = null;
        this.sourceCodePath = null;
        this.projectName = null;
        this.clientId = null;
        this.apiKey = null;
    }

    private void loadFromEnvVariables() {
        this.reset();
        this.baseURI = System.getenv(Constants.SOOS_API_BASE_URI);
        this.sourceCodePath = System.getenv(Constants.SOOS_ROOT_CODE_PATH);
        this.projectName = System.getenv(Constants.SOOS_PROJECT_NAME);
        this.clientId = System.getenv(Constants.SOOS_CLIENT_ID);
        this.apiKey = System.getenv(Constants.SOOS_API_KEY);
    }

    private void loadFromArgs(Object args) {

    }

    public boolean load(Object args) {
        this.loadFromEnvVariables();

        if(!ContextValidator.validate(this)) {
            this.loadFromArgs(args);

            return ContextValidator.validate(this);
        }

        return true;

    }
}
