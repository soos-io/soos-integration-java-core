package io.soos.integration.domain.structure;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.soos.integration.builders.StructureURIBuilder;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.Context;
import io.soos.integration.domain.RequestParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;

public class Structure {
    private final Logger LOG = LoggerFactory.getLogger(Structure.class);

    protected Context context;

    public Structure(Context context) {
        this.context = context;
    }

    private String generateURL() {
        StructureURIBuilder apiBuilder = new StructureURIBuilder();

        return apiBuilder
                .baseURI(this.context.getBaseURI())
                .clientId(this.context.getClientId())
                .buildURI();
    }

    public StructureResponse execute() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String urlAddress = this.generateURL();
        this.LOG.info("Creating Analysis Structure");
        this.LOG.info("Performing request to: {}", urlAddress);

        StructureAPIRequestBody body = new StructureAPIRequestBody(this.context);

        RequestParams params = new RequestParams(urlAddress, this.context.getApiKey(), "POST", objectMapper.writeValueAsString(body));

        String response = Utils.performRequest(params);

        LinkedHashMap soosResponse = objectMapper.readValue(response, LinkedHashMap.class);

        List errors = (List) soosResponse.get("errors");

        if (errors.size() > 0) {
            throw new Exception(errors.toString());
        }

        return new StructureResponse(soosResponse);
    }
}
