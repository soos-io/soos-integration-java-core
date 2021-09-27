package io.soos.integration.domain.analysis;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.soos.integration.builders.AnalysisStartURIBuilder;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.Context;
import io.soos.integration.domain.RequestParams;

import java.util.LinkedHashMap;

public class AnalysisStart {

    public static String generateAPIURL(Context context, String projectId, String analysisId) {
        AnalysisStartURIBuilder uriBuilder = new AnalysisStartURIBuilder();

        return uriBuilder.baseURI(context.getBaseURI())
                .clientId(context.getClientId())
                .projectId(projectId)
                .analysisId(analysisId)
                .buildURI();
    }

    public static LinkedHashMap exec(Context context, String projectId, String analysisId) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String apiURL = AnalysisStart.generateAPIURL(context, projectId, analysisId);

        RequestParams params = new RequestParams(apiURL, context.getApiKey(), "PUT");

        String response = Utils.performUploadFileRequest(params);

        LinkedHashMap soosResponse = objectMapper.readValue(response, LinkedHashMap.class);

        return soosResponse;

    }
}
