package io.soos.integration.domain.scan;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.soos.integration.builders.CreateScanURIBuilder;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.Context;
import io.soos.integration.domain.RequestParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;

public class Scan {
    private final Logger LOG = LoggerFactory.getLogger(Scan.class);

    protected Context context;

    public Scan(Context context) {
        this.context = context;
    }

    private String generateURL() {
        CreateScanURIBuilder apiBuilder = new CreateScanURIBuilder();

        return apiBuilder
                .baseURI(this.context.getBaseURI())
                .clientId(this.context.getClientId())
                .scanType("Sca")
                .buildURI();
    }

    public ScanResponse execute() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String urlAddress = this.generateURL();
        this.LOG.info("Creating Analysis Structure");
        this.LOG.info("Performing request to: {}", urlAddress);

        ScanAPIRequestBody body = new ScanAPIRequestBody(this.context);

        RequestParams params = new RequestParams(urlAddress, this.context.getApiKey(), "POST", objectMapper.writeValueAsString(body));

        String response = Utils.performRequest(params);

        LinkedHashMap soosResponse = objectMapper.readValue(response, LinkedHashMap.class);

        List errors = (List) soosResponse.get("errors");

        if (errors != null && errors.size() > 0) {
            throw new Exception(errors.toString());
        }

        return new ScanResponse(soosResponse);
    }
}
