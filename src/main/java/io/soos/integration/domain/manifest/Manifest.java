package io.soos.integration.domain.manifest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.soos.integration.builders.ManifestTypesURIBuilder;
import io.soos.integration.builders.ManifestURIBuilder;
import io.soos.integration.commons.ManifestFilesFilter;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.Context;
import io.soos.integration.domain.RequestParams;
import io.soos.integration.domain.RequestParamsManifest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
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


    private String getManifestLabel(Path file) {
        try {
            return URLEncoder.encode(file.getParent().getFileName().toString(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException exception) {
            this.LOG.error(exception.getMessage());
            return "";
        }
    }


    private String getManifestName(Path file) {
        try {
            return URLEncoder.encode(file.getFileName().toString().replace(".", "*"), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException exception) {
            this.LOG.error(exception.getMessage());
            return "";
        }
    }


    private List<Path> getFilesPath(String pattern, List<File> directoriesToExclude, List<File> filesToExclude) {
        String codeRoot = this.context.getSourceCodePath();

        if (StringUtils.isEmpty(codeRoot)) {
            codeRoot = Utils.getCurrentDirectory();
        }

        File[] files = Paths.get(codeRoot).toFile().listFiles(new ManifestFilesFilter(pattern, directoriesToExclude, filesToExclude));

        return Utils.convertArrayToList(files, Function.identity()).stream()
                .map(file -> Paths.get(file.toURI()))
                .collect(Collectors.toList());
    }


    public long sendManifests(String projectId, String analysisId, List<File> directoriesToExclude, List<File> filesToExclude) throws Exception {

        List<ManifestResponse> results = new ArrayList<>();

        this.LOG.info("-------------------------------");
        this.LOG.info("Begin Recursive Manifest Search");
        this.LOG.info("-------------------------------");

        ManifestTypesResponse manifestTypes = this.getManifestTypes();

        manifestTypes.getManifests().forEach((packageManager, manifestFiles) -> {
            this.LOG.info("--------------------------------------------------------");
            this.LOG.info("Looking for {} files...", packageManager);
            List<Path> paths = manifestFiles.stream()
                    .map(ManifestTypeDetail::getPattern)
                    .map((pattern) -> this.getFilesPath(pattern, directoriesToExclude, filesToExclude))
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
            if(paths.size() > 0) {
                this.LOG.info("Files: {}", paths.stream().map(path -> path.getFileName().toString()).collect(Collectors.toList()));
            } else {
                this.LOG.info("No files found.");
            }

            results.addAll(paths.stream().map(file -> {
                try {
                    return this.exec(projectId, analysisId, file);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }).collect(Collectors.toList()));

            this.LOG.info("--------------------------------------------------------");

        });

        return results.stream().filter(Objects::nonNull).count();
    }

    private String generateManifestURL(String projectId, String analysisId, String manifestLabel, String manifestName) {
        ManifestURIBuilder apiBuilder = new ManifestURIBuilder();

        return apiBuilder
                .baseURI(this.context.getBaseURI())
                .clientId(this.context.getClientId())
                .projectId(projectId)
                .analysisId(analysisId)
                .manifestLabel(manifestLabel)
                .manifestName(manifestName)
                .buildURI();
    }

    public ManifestResponse exec(String projectId, String analysisId, Path manifestPath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String manifestLabel = this.getManifestLabel(manifestPath);
        String manifestName = this.getManifestName(manifestPath);
        String apiURL = this.generateManifestURL(projectId, analysisId, manifestLabel, manifestName);

        this.LOG.info("Send Manifest URL: " + apiURL);

        RequestParamsManifest params = new RequestParamsManifest(apiURL, this.context.getApiKey(), "PUT", manifestPath);

        String response = Utils.uploadManifestFile(params);

        LinkedHashMap soosResponse = objectMapper.readValue(response, LinkedHashMap.class);

        return new ManifestResponse(soosResponse);
    }

}
