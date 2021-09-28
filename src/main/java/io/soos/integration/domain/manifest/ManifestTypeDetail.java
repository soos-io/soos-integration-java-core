package io.soos.integration.domain.manifest;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ManifestTypeDetail {
    protected String pattern;
    protected boolean isLockFile;

    public ManifestTypeDetail(String pattern, boolean isLockFile) {
        this.pattern = pattern;
        this.isLockFile = isLockFile;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isLockFile() {
        return isLockFile;
    }

    public void setLockFile(boolean lockFile) {
        isLockFile = lockFile;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ManifestTypeDetail{");
        sb.append("pattern='").append(pattern).append('\'');
        sb.append(", isLockFile=").append(isLockFile);
        sb.append('}');
        return sb.toString();
    }
}
