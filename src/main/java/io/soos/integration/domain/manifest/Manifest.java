package io.soos.integration.domain.manifest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.soos.integration.builders.ManifestTypesURLBuilder;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.Context;
import io.soos.integration.domain.RequestParams;
import io.soos.integration.domain.structure.Structure;
import io.soos.integration.domain.structure.StructureAPIRequestBody;
import io.soos.integration.domain.structure.StructureAPIResponseBody;
import io.soos.integration.domain.structure.StructureResponse;

public class Manifest {

    private static String generateManifestTypesURL(Context context) {
        ManifestTypesURLBuilder apiBuilder = new ManifestTypesURLBuilder();

        return apiBuilder
                .baseURI(context.getBaseURI())
                .clientId(context.getClientId())
                .buildURI();
    }

    public static ManifestTypesResponse getManifestTypes(Context context) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String urlAddress = Manifest.generateManifestTypesURL(context);

        RequestParams params = new RequestParams(urlAddress, context.getApiKey(), "GET");

        String response = Utils.performRequest(params);

        ManifestTypesAPIResponse[] soosResponse = objectMapper.readValue(response, ManifestTypesAPIResponse[].class);

        return new ManifestTypesResponse(soosResponse);

    }

    public static int sendManifests() {
        return 0;
    }

}
