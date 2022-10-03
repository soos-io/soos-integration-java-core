package io.soos.integration.domain;

public enum PackageManagers {
    DART("Dart"), ERLANG("Erlang"), HOMEBREW("Homebrew"), JAVA("Java"), NPM("NPM"), NUGET("NuGet"), PHP("PHP"), PYTHON("Python"), RUBY("Ruby"), RUST("Rust"), GO("Go");

    private String packageManager;

    PackageManagers(String packageManager) {
        this.packageManager = packageManager;
    }

    public String getPackageManager() {
        return packageManager;
    }
}
