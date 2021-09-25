package io.soos.integration.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.soos.integration.builders.StructureURIBuilder;
import io.soos.integration.commons.Utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

public class Structure {

    private static String generateAPIURL(Context context) {
        StructureURIBuilder apiBuilder = new StructureURIBuilder();

        return apiBuilder
                .baseURI(context.getBaseURI())
                .clientId(context.getClientId())
                .buildURI();
    }

    public static Object execute(Context context) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String urlAddress = Structure.generateAPIURL(context);

        StructureBody body = new StructureBody(context);

        RequestParams params = new RequestParams(urlAddress, context.getApiKey(), "POST", objectMapper.writeValueAsString(body));

        String response = Utils.performRequest(params);

    }
}
