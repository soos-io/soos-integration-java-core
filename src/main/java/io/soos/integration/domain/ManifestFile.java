package io.soos.integration.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ManifestFile {
    RUBY("ruby", Arrays.asList("Gemfile", "Gemfile.lock")),
    PYTHON("python", Arrays.asList("requirements.txt", "pipfile", "*.toml", "poetry.lock")),
    NPM("npm", Arrays.asList("package.json", "package-lock.json", "yarn.lock")),
    JAVA("java", Collections.singletonList("pom.xml")),
    NUGET("NuGet", Arrays.asList("packages.config", "*.csproj", "project.assets.json", "*packages.lock.json", "packet*"));

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
