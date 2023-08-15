package io.soos.integration.domain;

public class ContributingDeveloperAudit {
    protected String source;
    protected String sourceName;
    protected String contributingDeveloperId;

    public ContributingDeveloperAudit(String source, String sourceName, String contributingDeveloperId) {
        this.source = source;
        this.sourceName = sourceName;
        this.contributingDeveloperId = contributingDeveloperId;
    }

    public String getSource() {
        return source;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getContributingDeveloperId() {
        return contributingDeveloperId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void setContributingDeveloperId(String contributingDeveloperId) {
        this.contributingDeveloperId = contributingDeveloperId;
    }
}
