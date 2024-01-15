package io.soos.integration.domain;

import io.soos.integration.commons.Constants;
import io.soos.integration.commons.Utils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Script {
    protected File codeRoot;
    protected File asyncResult;
    protected OnFailure onFailure;
    protected List<File> directoriesToExclude;
    protected List<File> filesToExclude;
    protected File workspaceDirectory;
    protected int analysisResultMaxWait;
    protected int analysisResultPoolingInterval;
    protected List<PackageManagers> packageManagers;

    private final Map<String, String> params;

    public Script() {
        this.codeRoot = new File(Utils.getCurrentDirectory());
        this.params = Utils.parseArgs();

        this.load();
    }

    public void load() {
        this.setOnFailure();
        this.setDirectoriesToExclude();
        this.setFilesToExclude();
        this.setWorkspaceDirectory();
        this.setAnalysisResultMaxWait();
        this.setAnalysisResultPoolingInterval();
        this.setPackageManagers();
    }

    public void setPackageManagers() throws IllegalArgumentException {
        String packageManagers = this.params.get(Constants.MAP_PARAM_PACKAGE_MANAGERS_KEY);
        this.packageManagers = new ArrayList<>();
        if (StringUtils.isNotBlank(packageManagers)) {
            List<String> packageManagersList = Arrays.stream(packageManagers.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
            String validOptions = Arrays.stream(PackageManagers.values())
                    .map(Enum::name)
                    .collect(Collectors.joining(", "));
            for (String packageManager : packageManagersList) {
                PackageManagers matchedPackageManager = Arrays.stream(PackageManagers.values())
                        .filter(e -> e.name().equalsIgnoreCase(packageManager))
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Invalid package manager: " + packageManager +
                                        ", valid options are: " + validOptions));
                this.packageManagers.add(matchedPackageManager);
            }
        } else {
            this.packageManagers = null;
        }
    }


    private void setOnFailure() {
        String onFailure = this.params.get(Constants.MAP_PARAM_ON_FAILURE_KEY);
        switch (onFailure) {
            case "continue_on_failure":
                this.onFailure = OnFailure.CONTINUE_ON_FAILURE;
                break;
            default:
                this.onFailure = OnFailure.FAIL_THE_BUILD;
                break;
        }
    }

    private void setDirectoriesToExclude() {
        String dirsToExclude = this.params.get(Constants.MAP_PARAM_DIRS_TO_EXCLUDE_KEY);
        this.directoriesToExclude = new ArrayList<>();
        if(StringUtils.isNoneEmpty(dirsToExclude)) {
            this.directoriesToExclude.addAll(Arrays.stream(dirsToExclude.split(","))
                    .map(String::trim).map(File::new).collect(Collectors.toList()));
        }
    }

    private void setFilesToExclude() {
        String filesToExclude = this.params.get(Constants.MAP_PARAM_FILES_TO_EXCLUDE_KEY);
        this.filesToExclude = new ArrayList<>();
        if(StringUtils.isNoneEmpty(filesToExclude)) {
            this.filesToExclude.addAll(Arrays.stream(filesToExclude.split(","))
                    .map(String::trim).map(File::new).collect(Collectors.toList()));
        }
    }

    private void setWorkspaceDirectory() {
        String workspaceDir = this.params.get(Constants.MAP_PARAM_WORKSPACE_DIR_KEY);
        if(StringUtils.isNoneEmpty(workspaceDir)) {
            this.workspaceDirectory = Path.of(workspaceDir).toFile();
            this.asyncResult = Path.of(workspaceDir, Constants.DEFAULT_ASYNC_RESULT_FILE_NAME).toFile();
        } else {
            Path filePath = Path.of(this.codeRoot.getAbsolutePath(), Constants.DEFAULT_ASYNC_RESULT_FILE_NAME);
            this.asyncResult = filePath.toFile();
        }
    }

    private void setAnalysisResultMaxWait() {
        String analysisResultMaxWait = this.params.get(Constants.MAP_PARAM_ANALYSIS_RESULT_MAX_WAIT_KEY);
        if(StringUtils.isNoneEmpty(analysisResultMaxWait)) {
            this.analysisResultMaxWait = Integer.parseInt(analysisResultMaxWait);
        }
    }

    private void setAnalysisResultPoolingInterval() {
        String analysisResultPollingInterval = this.params.get(Constants.MAP_PARAM_ANALYSIS_RESULT_POLLING_INTERVAL_KEY);
        if(StringUtils.isNoneEmpty(analysisResultPollingInterval)) {
            this.analysisResultPoolingInterval = Integer.parseInt(analysisResultPollingInterval);
        }
    }

    public File getCodeRoot() {
        return codeRoot;
    }

    public File getAsyncResult() {
        return asyncResult;
    }

    public OnFailure getOnFailure() {
        return onFailure;
    }

    public List<File> getDirectoriesToExclude() {
        return directoriesToExclude;
    }

    public List<File> getFilesToExclude() {
        return filesToExclude;
    }

    public File getWorkspaceDirectory() {
        return workspaceDirectory;
    }

    public int getAnalysisResultMaxWait() {
        return analysisResultMaxWait;
    }

    public int getAnalysisResultPoolingInterval() {
        return analysisResultPoolingInterval;
    }

    public List<PackageManagers> getPackageManagers() { return packageManagers; }
}
