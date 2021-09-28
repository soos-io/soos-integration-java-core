package io.soos.integration.domain.analysis;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.Context;
import io.soos.integration.domain.RequestParams;

import java.util.LinkedHashMap;

public class AnalysisResult {

    protected Context context;

    public AnalysisResult(Context context) {
        this.context = context;
    }

    public AnalysisResultResponse execute(String reportStatusURL) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        RequestParams params = new RequestParams(reportStatusURL, this.context.getApiKey(), "GET");

        String response = Utils.performRequest(params);

        LinkedHashMap<String, Object> soosResponse = objectMapper.readValue(response, LinkedHashMap.class);

        return new AnalysisResultResponse(soosResponse);
    }
}
