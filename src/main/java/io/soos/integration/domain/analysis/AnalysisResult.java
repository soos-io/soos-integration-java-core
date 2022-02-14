package io.soos.integration.domain.analysis;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.soos.integration.commons.Constants;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.Context;
import io.soos.integration.domain.RequestParams;

public class AnalysisResult {

    private final Logger LOG = LoggerFactory.getLogger(AnalysisResult.class);

    protected Context context;

    public AnalysisResult(Context context) {
        this.context = context;
    }

    public AnalysisResultResponse execute(String reportStatusURL) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestParams params = new RequestParams(reportStatusURL, this.context.getApiKey(), "GET");
        
        LinkedHashMap<String, Object> soosResponse;

            
        while(this.context.getAnalysisResultMaxWait() >= 0){
            String response = Utils.performRequest(params);
            soosResponse = objectMapper.readValue(response, LinkedHashMap.class);
            String status = "";
            if(soosResponse.containsKey("status")) {
                status = soosResponse.get("status").toString();
            } 
            if(Arrays.asList(Constants.REPORT_STATUS_FINISHED, Constants.REPORT_STATUS_FAILED, Constants.REPORT_STATUS_FailedWithIssues).contains(status)){
                return new AnalysisResultResponse(soosResponse);
            } 

            StringBuilder sb = new StringBuilder().append("Analysis is running, trying again in ")
                                                    .append(this.context.getAnalysisResultPoolInterval())
                                                    .append(" seconds...");

            this.LOG.info(sb.toString());
            TimeUnit.SECONDS.sleep(this.context.getAnalysisResultPoolInterval());
            int resultMaxWait = this.context.getAnalysisResultMaxWait() - this.context.getAnalysisResultPoolInterval();
            this.context.setAnalysisResultMaxWait(resultMaxWait);
            
        }
        throw new Exception("Could not get a response from SOOS");
    }
}
