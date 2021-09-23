package io.soos.integration.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ManifestFile {
    RUBY("ruby", Collections.singletonList("Gemfile")),
    PYTHON("python", Arrays.asList("requirements.txt", "pipfile")),
    JAVASCRIPT("npm", Arrays.asList("package.json", "package-lock.json")),
    JAVA("java", Collections.singletonList("pom.xml")),
    NET("NuGet", Arrays.asList("Packages.config", "*.csproj"));

    private String name;
    private List<String> files;

    ManifestFile(String name, List<String> files) {
        this.name = name;
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public List<String> getFiles() {
        return files;
    }
}
