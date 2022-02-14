package io.soos.integration.domain.scan;

import io.soos.integration.domain.Context;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScanAPIRequestBody {
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
    private String scriptVersion;
    private String toolName;


    public ScanAPIRequestBody(Context context) {
        String pattern = "MM/dd/yyyy, hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String name = simpleDateFormat.format(new Date());
        this.project = context.getProjectName();
        this.name = name;
        this.integrationType = context.getIntegrationType();
        this.scriptVersion = context.getScriptVersion();
        this.initialize(context);
    }

    private void initialize(Context context) {
        if(StringUtils.isNotEmpty(context.getBranchURI())) {
            this.setBranchUri(context.getBranchURI());
        }

        if(StringUtils.isNotEmpty(context.getBranchName())) {
            this.setBranch(context.getBranchName());
        }

        if(StringUtils.isNotEmpty(context.getCommitHash())) {
            this.setCommitHash(context.getCommitHash());
        }

        if(StringUtils.isNotEmpty(context.getCommitHash())) {
            this.setBuildVersion(context.getCommitHash());
        }

        if(StringUtils.isNotEmpty(context.getBuildURI())) {
            this.setBuildUri(context.getBuildURI());
        }

        if(StringUtils.isNotEmpty(context.getOperatingEnvironment())) {
            this.setOperatingEnvironment(context.getOperatingEnvironment());
        }

        if(StringUtils.isNotEmpty(context.getIntegrationName())) {
            this.setIntegrationName(context.getIntegrationName());
        }
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

    public String getScriptVersion() {
      return scriptVersion;
    }

    public void setScriptVersion(String scriptVersion) {
      this.scriptVersion = scriptVersion;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("project", project)
                .append("name", name)
                .append("integrationType", integrationType)
                .append("branchUri", branchUri)
                .append("branch", branch)
                .append("commitHash", commitHash)
                .append("buildVersion", buildVersion)
                .append("buildUri", buildUri)
                .append("operatingEnvironment", operatingEnvironment)
                .append("integrationName", integrationName)
                .append("scriptVersion", scriptVersion)
                .append("toolName", toolName)
                .toString();
    }
}
