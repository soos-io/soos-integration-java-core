package io.soos.integration.domain.analysis;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.soos.integration.builders.AnalysisStartURIBuilder;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.Context;
import io.soos.integration.domain.RequestParams;

public class AnalysisStart {
    private final Logger LOG = LoggerFactory.getLogger(AnalysisStart.class);
    protected Context context;

    public AnalysisStart(Context context) {
        this.context = context;
    }

    public String generateAPIURL(String projectId, String analysisId) {
        AnalysisStartURIBuilder uriBuilder = new AnalysisStartURIBuilder();

        return uriBuilder.baseURI(this.context.getBaseURI())
                .clientId(this.context.getClientId())
                .projectId(projectId)
                .analysisId(analysisId)
                .buildURI();
    }

    public void execute(String projectId, String analysisId) throws Exception {
        String apiURL = this.generateAPIURL(projectId, analysisId);
        RequestParams params = new RequestParams(apiURL, context.getApiKey(), "PUT", "");
        Utils.analysisStartRequest(params);
    }
}
