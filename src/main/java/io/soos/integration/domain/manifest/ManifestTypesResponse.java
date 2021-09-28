package io.soos.integration.domain.manifest;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;
import java.util.stream.Collectors;

public class ManifestTypesResponse {
    protected Map<String, List<ManifestTypeDetail>> manifests;


    public ManifestTypesResponse(ArrayList<LinkedHashMap<String, Object>> response) {
        this.manifests = response.stream()
                .collect(Collectors.toMap(manifest -> manifest.get("packageManager").toString(),
                        manifest -> ((ArrayList<LinkedHashMap>) manifest.get("manifests"))
                                .stream().map(detail -> new ManifestTypeDetail(detail.get("pattern").toString(), (boolean) detail.get("isLockFile"))).collect(Collectors.toList())));
    }


    public List<ManifestTypeDetail> get(String type) {
        return this.manifests.get(type);
    }

    public List<String> getFilePatterns(String type) {
        return this.get(type).stream().map(ManifestTypeDetail::getPattern).collect(Collectors.toList());
    }

    public Map<String, List<ManifestTypeDetail>> getManifests() {
        return manifests;
    }

    public void setManifests(Map<String, List<ManifestTypeDetail>> manifests) {
        this.manifests = manifests;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ManifestTypesResponse{");
        sb.append("manifests=").append(manifests);
        sb.append('}');
        return sb.toString();
    }
}
