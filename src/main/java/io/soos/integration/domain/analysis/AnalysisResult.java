package io.soos.integration.domain.analysis;

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
        
        LinkedHashMap<String, Object> soosResponse = null;
        int resultMaxWait = Integer.parseInt(System.getProperty(Constants.PARAM_ANALYSIS_RESULT_MAX_WAIT_KEY));
        int resultPollingInt = Integer.parseInt(System.getProperty(Constants.PARAM_ANALYSIS_RESULT_POLLING_INTERVAL_KEY));

            
        while(resultMaxWait >= 0){
            String response = Utils.performRequest(params);
            soosResponse = objectMapper.readValue(response, LinkedHashMap.class);
            String status = "";
            if(soosResponse.containsKey("status")) {
                status = soosResponse.get("status").toString();
            } 
            if(status.equals("Finished") || status.equals("Failed")){
                return new AnalysisResultResponse(soosResponse);
            } 

            this.LOG.info("Analysis is running, trying again in ".concat(String.valueOf(resultPollingInt)).concat(" seconds..."));
            TimeUnit.SECONDS.sleep(resultPollingInt);
            resultMaxWait -= resultPollingInt;
            
        }
        throw new Exception("Could not get a response from SOOS");
    }
}
