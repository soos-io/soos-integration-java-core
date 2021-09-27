package io.soos.integration.domain.structure;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.soos.integration.builders.StructureURIBuilder;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.Context;
import io.soos.integration.domain.RequestParams;

import java.util.LinkedHashMap;
import java.util.List;

public class Structure {

    private static String generateURL(Context context) {
        StructureURIBuilder apiBuilder = new StructureURIBuilder();

        return apiBuilder
                .baseURI(context.getBaseURI())
                .clientId(context.getClientId())
                .buildURI();
    }

    public static StructureResponse execute(Context context) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String urlAddress = Structure.generateURL(context);

        StructureAPIRequestBody body = new StructureAPIRequestBody(context);

        RequestParams params = new RequestParams(urlAddress, context.getApiKey(), "POST", objectMapper.writeValueAsString(body));

        String response = Utils.performRequest(params);

        LinkedHashMap soosResponse = objectMapper.readValue(response, LinkedHashMap.class);

        List errors = (List) soosResponse.get("errors");

        if(errors.size() > 0) {
            throw new Exception(errors.toString());
        }

        return new StructureResponse(soosResponse);
    }
}
