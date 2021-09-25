package io.soos.integration.builders;

public interface ISOOSURIBuilder<T> {
    public T baseURI(String baseURI);
    public T clientId(String clientId);
    public String buildURI();
}
