package io.soos.integration.domain;

public class RequestParams {
    protected String url;
    protected String apiKey;
    protected String method;
    protected String body;

    public RequestParams(String url, String apiKey, String method) {
        this(url, apiKey, method, null);
    }

    public RequestParams(String url, String apiKey, String method, String body) {
        this.url = url;
        this.apiKey = apiKey;
        this.method = method;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
