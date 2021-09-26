package io.soos.integration.domain.manifest;

public class ManifestTypesAPIResponse {
    protected String packageManager;
    protected ManifestTypeDetail[] manifests;

    public ManifestTypesAPIResponse(String packageManager) {

        this(packageManager, new ManifestTypeDetail[0]);
    }

    public ManifestTypesAPIResponse(String packageManager, ManifestTypeDetail[] manifests) {
        this.packageManager = packageManager;
        this.manifests = manifests;
    }

    public String getPackageManager() {
        return packageManager;
    }

    public void setPackageManager(String packageManager) {
        this.packageManager = packageManager;
    }

    public ManifestTypeDetail[] getManifests() {
        return manifests;
    }

    public void setManifests(ManifestTypeDetail[] manifests) {
        this.manifests = manifests;
    }
}
