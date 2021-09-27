package io.soos.integration.domain.manifest;

import java.util.ArrayList;
import java.util.List;

public class ManifestTypesAPIResponse {
    protected String packageManager;
    protected List<ManifestTypeDetail> manifests;

    public ManifestTypesAPIResponse(String packageManager) {

        this(packageManager, new ArrayList<>());
    }

    public ManifestTypesAPIResponse(String packageManager, List<ManifestTypeDetail> manifests) {
        this.packageManager = packageManager;
        this.manifests = manifests;
    }

    public String getPackageManager() {
        return packageManager;
    }

    public void setPackageManager(String packageManager) {
        this.packageManager = packageManager;
    }

    public List<ManifestTypeDetail> getManifests() {
        return manifests;
    }

    public void setManifests(List<ManifestTypeDetail> manifests) {
        this.manifests = manifests;
    }
}
