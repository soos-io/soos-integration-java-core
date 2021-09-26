package io.soos.integration.domain.analysis;

import io.soos.integration.domain.Mode;
import io.soos.integration.domain.OnFailure;

import java.util.List;

public class AnalysisScript {
    protected String codeRoot;
    protected String asyncResultFile;
    protected Mode mode;
    protected OnFailure onFailure;
    protected List<String> directoriesToExclude;
    protected List<String> filesToExclude;
    protected String workingDirectory;
    protected int analysisResultMaxWait;
    protected int analysisResultPoolingInterval;

    public AnalysisScript() {
    }

    public void load(String[] args) {

    }
}
