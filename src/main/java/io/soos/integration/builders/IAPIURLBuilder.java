package io.soos.integration.builders;

public interface IAPIURLBuilder<T> {
    public T baseURL(String baseURL);
    public T clientId(String clientId);
    public String buildURL();
}
