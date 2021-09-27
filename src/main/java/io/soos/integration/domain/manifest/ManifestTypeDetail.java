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
        return new ToStringBuilder(this)
                .append("pattern", pattern)
                .append("isLockFile", isLockFile)
                .toString();
    }
}
