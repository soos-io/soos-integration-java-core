package io.soos.integration.domain.manifest;

import io.soos.integration.commons.Utils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ManifestTypesResponse {
    protected Map<String, List<ManifestTypeDetail>> manifests;


    public ManifestTypesResponse(ArrayList<LinkedHashMap<String, Object>> response) {
        this.manifests = new HashMap<>();
    }


    public List<ManifestTypeDetail> get(String type) {
        return this.manifests.get(type);
    }

    public List<String> getFilePatterns(String type) {
        return this.get(type).stream().map(ManifestTypeDetail::getPattern).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("manifests", manifests)
                .toString();
    }
}
