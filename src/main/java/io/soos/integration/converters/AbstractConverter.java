package io.soos.integration.converters;

public abstract class AbstractConverter<S, T> implements IConverter<S, T> {

    protected S source;

    public AbstractConverter(S source) {
        this.source = source;
    }
}
