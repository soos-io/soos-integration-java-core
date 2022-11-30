package io.soos.integration.domain.manifest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.soos.integration.builders.ManifestTypesURIBuilder;
import io.soos.integration.builders.ManifestURIBuilder;
import io.soos.integration.commons.Constants;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.Context;
import io.soos.integration.domain.PackageManagers;
import io.soos.integration.domain.RequestParams;
import io.soos.integration.domain.RequestParamsManifest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Manifest {
    private final Logger LOG = LoggerFactory.getLogger(Manifest.class);
    protected Context context;

    public Manifest(Context context) {
        this.context = context;
    }

    private static String generateManifestTypesURL(Context context) {
        ManifestTypesURIBuilder apiBuilder = new ManifestTypesURIBuilder();

        return apiBuilder
                .baseURI(context.getBaseURI())
                .clientId(context.getClientId())
                .buildURI();
    }

    public ManifestTypesResponse getManifestTypes() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String urlAddress = Manifest.generateManifestTypesURL(this.context);

        RequestParams params = new RequestParams(urlAddress, this.context.getApiKey(), "GET");

        String response = Utils.performRequest(params);

        ArrayList<LinkedHashMap<String, Object>> soosResponse = objectMapper.readValue(response, ArrayList.class);

        return new ManifestTypesResponse(soosResponse);
    }


    private List<Path> getFilesPath(String pattern, List<File> directoriesToExclude, List<File> filesToExclude) throws IOException {
        String codeRoot = this.context.getSourceCodePath();

        if (StringUtils.isEmpty(codeRoot)) {
            codeRoot = Utils.getCurrentDirectory();
        }

        List<Path> paths = new ArrayList<>();
        Files.find(Paths.get(codeRoot), Integer.MAX_VALUE,(filepath, fileattr) -> fileattr.isRegularFile()).forEach(p -> paths.add(p));

        return paths.stream().filter(f -> Utils.manifestFileIsValid(f.toFile(), pattern, directoriesToExclude, filesToExclude)).collect(Collectors.toList());
    }


    public long sendManifests(String projectId, String analysisId, List<File> directoriesToExclude, List<File> filesToExclude, List<PackageManagers> packageManagers) throws Exception {

        this.LOG.info("-------------------------------");
        this.LOG.info("Begin Recursive Manifest Search");
        this.LOG.info("-------------------------------");

        final Map<String, List<Path>> allPaths = new HashMap<String, List<Path>>();
        ManifestTypesResponse manifestTypes = this.getManifestTypes();

        manifestTypes.getManifests().forEach((packageManager, manifestFiles) -> {
            if(packageManagers != null && packageManagers.size() > 0 && packageManagers.stream().filter(e -> e.name().equalsIgnoreCase(packageManager)).findAny().orElse(null) == null){
                return;
            }
            this.LOG.info("--------------------------------------------------------");
            this.LOG.info("Looking for {} files...", packageManager);
            List<Path> packageManagerPaths = manifestFiles.stream()
                    .map(ManifestTypeDetail::getPattern)
                    .map((pattern) -> {
                        try {
                            return this.getFilesPath(pattern, directoriesToExclude, filesToExclude);
                        }catch(IOException e){
                            this.LOG.error("Error during manifest search");
                            throw new UncheckedIOException(e);
                        }
                    })
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            if (packageManagerPaths.size() > 0) {
                this.LOG.info("Files: {}", packageManagerPaths.stream().map(path -> path.getFileName().toString()).collect(Collectors.toList()));
                allPaths.merge(packageManager, packageManagerPaths, (oldValue, newValue) -> {
                    oldValue.addAll(newValue);
                    return oldValue;
                });
            } else {
                this.LOG.info("No files found.");
            }
        });

        this.LOG.info("--------------------------------------------------------");
        int totalManifests = 0;
        for (Map.Entry<String, List<Path>> entry : allPaths.entrySet()) {
            if(totalManifests == Constants.MAX_MANIFESTS){
                this.LOG.info("Maximum number of manifests reached ({}). Skipping remaining manifests.", Constants.MAX_MANIFESTS);
                break;
            }
            String packageManager = entry.getKey();
            List<Path> pathsToUpload = entry.getValue();
            totalManifests += pathsToUpload.size();
            try {
                boolean hasMoreThanMaximumManifests = totalManifests > Constants.MAX_MANIFESTS;
                if (hasMoreThanMaximumManifests) {
                    pathsToUpload = pathsToUpload.subList(0, Constants.MAX_MANIFESTS - (totalManifests - pathsToUpload.size()));
                    this.LOG.info("Maximum number of manifests reached ({}). Skipping remaining manifests.", Constants.MAX_MANIFESTS)
                }
                if (pathsToUpload.size() > 0) {
                    this.LOG.info("Uploading {} {} manifests...", pathsToUpload.size(), packageManager);
                    this.exec(projectId, analysisId, pathsToUpload, hasMoreThanMaximumManifests);
                }
                if(hasMoreThanMaximumManifests){
                    // break the loop since max manifest has been reached
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Math.min(totalManifests, Constants.MAX_MANIFESTS);
    }

    private String generateManifestURL(String projectId, String analysisId, boolean hasMoreThanMaximumManifests) {
        ManifestURIBuilder apiBuilder = new ManifestURIBuilder();

        return apiBuilder
                .baseURI(this.context.getBaseURI())
                .clientId(this.context.getClientId())
                .projectId(projectId)
                .analysisId(analysisId)
                .hasMoreThanMaximumManifests(hasMoreThanMaximumManifests)
                .buildURI();
    }

    public ManifestResponse exec(String projectId, String analysisId, List<Path> manifestPaths, boolean hasMoreThanMaximumManifests) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String apiURL = this.generateManifestURL(projectId, analysisId, hasMoreThanMaximumManifests);

        this.LOG.info("Send Manifest URL: " + apiURL);

        RequestParamsManifest params = new RequestParamsManifest(apiURL, this.context.getApiKey(), "POST", manifestPaths);

        String response = Utils.uploadManifestFile(params);

        LinkedHashMap soosResponse = objectMapper.readValue(response, LinkedHashMap.class);

        return new ManifestResponse(soosResponse);
    }

}
