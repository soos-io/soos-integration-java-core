package io.soos.integration.domain.manifest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.soos.integration.builders.ManifestTypesURIBuilder;
import io.soos.integration.builders.ManifestURIBuilder;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.Context;
import io.soos.integration.domain.RequestParams;
import io.soos.integration.domain.RequestParamsManifest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
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

        return paths.stream().filter(f -> Utils.ManifestFileIsValid(f.toFile(), pattern, directoriesToExclude, filesToExclude)).collect(Collectors.toList());
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
                    .map((pattern) -> {
                        try {
                            return this.getFilesPath(pattern, directoriesToExclude, filesToExclude);
                        } catch (IOException e) {
                           this.LOG.error("Error on manifest search");
                           System.exit(1);
                        }
                        return new ArrayList<Path>();
                    })
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
            if(paths.size() > 0) {
                this.LOG.info("Files: {}", paths.stream().map(path -> path.getFileName().toString()).collect(Collectors.toList()));
            } else {
                this.LOG.info("No files found.");
                return;
            }


            try {
                results.add(this.exec(projectId, analysisId, paths));
            }catch(Exception ex){
                ex.printStackTrace();
            }

            this.LOG.info("--------------------------------------------------------");

        });

        return results.stream().filter(Objects::nonNull).count();
    }

    private String generateManifestURL(String projectId, String analysisId) {
        ManifestURIBuilder apiBuilder = new ManifestURIBuilder();

        return apiBuilder
                .baseURI(this.context.getBaseURI())
                .clientId(this.context.getClientId())
                .projectId(projectId)
                .analysisId(analysisId)
                .buildURI();
    }

    public ManifestResponse exec(String projectId, String analysisId, List<Path> manifestPaths) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String apiURL = this.generateManifestURL(projectId, analysisId);

        this.LOG.info("Send Manifest URL: " + apiURL);

        RequestParamsManifest params = new RequestParamsManifest(apiURL, this.context.getApiKey(), "POST", manifestPaths);

        String response = Utils.uploadManifestFile(params);

        LinkedHashMap soosResponse = objectMapper.readValue(response, LinkedHashMap.class);

        return new ManifestResponse(soosResponse);
    }

}
