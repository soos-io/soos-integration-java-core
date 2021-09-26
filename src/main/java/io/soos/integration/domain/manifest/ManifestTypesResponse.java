package io.soos.integration.domain.manifest;

import io.soos.integration.commons.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ManifestTypesResponse {
    protected Map<String, List<ManifestTypeDetail>> manifests;


    public ManifestTypesResponse(ManifestTypesAPIResponse[] response) {
        this.manifests = Arrays.stream(response)
                .collect(Collectors.toMap(ManifestTypesAPIResponse::getPackageManager, (ManifestTypesAPIResponse manifest) -> Utils.convertArrayToList(manifest.getManifests(), Function.identity())));
    }


    public List<ManifestTypeDetail> get(String type) {
        return this.manifests.get(type);
    }

    public List<String> getFilePatterns(String type) {
        return this.get(type).stream().map(ManifestTypeDetail::getPattern).collect(Collectors.toList());
    }




}
