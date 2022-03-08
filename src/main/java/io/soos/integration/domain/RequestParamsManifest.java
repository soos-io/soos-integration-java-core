package io.soos.integration.domain;


import java.nio.file.Path;
import java.util.List;

public class RequestParamsManifest extends RequestParams {
    protected List<Path> files;


    public RequestParamsManifest(String url, String apiKey, String method,
                                 List<Path> files) {
        super(url, apiKey, method, null);
        this.files = files;
    }

    public List<Path> getFiles() {
        return files;
    }

    public void setFiles(List<Path> files) {
        this.files = files;
    }
}
