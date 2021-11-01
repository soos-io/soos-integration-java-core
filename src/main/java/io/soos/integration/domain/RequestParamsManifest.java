package io.soos.integration.domain;


import java.nio.file.Path;

public class RequestParamsManifest extends RequestParams {
    protected Path file;


    public RequestParamsManifest(String url, String apiKey, String method, Path file) {
        super(url, apiKey, method, null);
        this.file = file;
    }

    public Path getFile() {
        return file;
    }

    public void setFile(Path file) {
        this.file = file;
    }
}
