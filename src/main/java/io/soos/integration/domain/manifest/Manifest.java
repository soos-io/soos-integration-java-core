package io.soos.integration.domain.manifest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.soos.integration.builders.ManifestTypesURIBuilder;
import io.soos.integration.builders.ManifestURIBuilder;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.Context;
import io.soos.integration.domain.RequestParams;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Manifest {

    private static String generateManifestTypesURL(Context context) {
        ManifestTypesURIBuilder apiBuilder = new ManifestTypesURIBuilder();

        return apiBuilder
                .baseURI(context.getBaseURI())
                .clientId(context.getClientId())
                .buildURI();
    }

    public static ArrayList<LinkedHashMap<String, Object>> getManifestTypes(Context context) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String urlAddress = Manifest.generateManifestTypesURL(context);

        RequestParams params = new RequestParams(urlAddress, context.getApiKey(), "GET");

        String response = Utils.performRequest(params);

        ArrayList<LinkedHashMap<String, Object>> soosResponse = objectMapper.readValue(response, ArrayList.class);

        return soosResponse;
    }



    public static int sendManifests(Context context, String projectId, String analysisId, List<File> directoriesToExclude, List<File> filesToExclude) {

        return 0;
    }

    private static String generateManifestURL(Context context, String projectId, String analysisId, String manifestLabel, String manifestName) {
        ManifestURIBuilder apiBuilder = new ManifestURIBuilder();

        return apiBuilder
                .baseURI(context.getBaseURI())
                .clientId(context.getClientId())
                .projectId(projectId)
                .analysisId(analysisId)
                .manifestLabel(manifestLabel)
                .manifestName(manifestName)
                .buildURI();
    }

    public static void exec(Context context, String projectId, String analysisId, String manifestLabel, String manifestName, File manifestContent) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String apiURL = Manifest.generateManifestURL(context, projectId, analysisId, manifestLabel, manifestName);

        RequestParams params = new RequestParams(apiURL, context.getApiKey(), "PUT");

        String response = Utils.performUploadFileRequest(params);
    }

}
