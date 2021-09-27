package io.soos.integration.domain.analysis;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.Context;
import io.soos.integration.domain.RequestParams;

import java.util.LinkedHashMap;

public class AnalysisResult {

    public static LinkedHashMap<String, Object> exec(Context context, String reportStatusURL) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        RequestParams params = new RequestParams(reportStatusURL, context.getApiKey(), "GET");

        String response = Utils.performRequest(params);

        LinkedHashMap<String, Object> soosResponse = objectMapper.readValue(response, LinkedHashMap.class);

        return soosResponse;
    }
}
