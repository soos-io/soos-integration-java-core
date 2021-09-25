package io.soos.integration.domain;

import io.soos.integration.commons.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StructureBody {
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


    public StructureBody(Context context) {
        String pattern = "MM/dd/yyyy, hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String name = simpleDateFormat.format(new Date());
        this.project = context.getProjectName();
        this.name = name;
        this.integrationType = context.getIntegrationType();
    }

    private void initialize(Context context) {
        if(StringUtils.isNoneEmpty(context.getBranchURI())) {
            this.setBranchUri(context.getBranchURI());
        }

        if(StringUtils.isNoneEmpty(context.getBranchName())) {
            this.setBranch(context.getBranchName());
        }

        if(StringUtils.isNoneEmpty(context.getCommitHash())) {
            this.setCommitHash(context.getCommitHash());
        }

        if(StringUtils.isNoneEmpty(context.getCommitHash())) {
            this.setBuildVersion(context.getCommitHash());
        }

        if(StringUtils.isNoneEmpty(context.getBuildURI())) {
            this.setBuildUri(context.getBuildURI());
        }

        if(StringUtils.isNoneEmpty(context.getOperatingEnvironment())) {
            this.setOperatingEnvironment(context.getOperatingEnvironment());
        }

        if(StringUtils.isNoneEmpty(context.getIntegrationName())) {
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
                .toString();
    }
}
