package io.soos.integration.domain;

import io.soos.integration.commons.Constants;

public class SOOSStructureAPIBody {
    private String project;
    private String name;
    private String integrationType;
    private String branchUri;
    private String branch;
    private String commitHash;
    private String buildVersion;
    private String buildUri;
    private String operatingEnvironment;
    private String integrationName;

    public SOOSStructureAPIBody(String project, String name) {
        this(project, name, Constants.INTEGRATION_TYPE);
    }

    public SOOSStructureAPIBody(String project, String name, String integrationType) {
        this.project = project;
        this.name = name;
        this.integrationType = integrationType;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }

    public String getBranchUri() {
        return branchUri;
    }

    public void setBranchUri(String branchUri) {
        this.branchUri = branchUri;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getBuildUri() {
        return buildUri;
    }

    public void setBuildUri(String buildUri) {
        this.buildUri = buildUri;
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
