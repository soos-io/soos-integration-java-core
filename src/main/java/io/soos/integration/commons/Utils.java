package io.soos.integration.commons;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

import io.soos.integration.domain.RequestParams;

public class Utils {

    public static String UrlEncode(String valueToEncode){
        // Method to encode a string value using `UTF-8` encoding scheme
        try {
            return URLEncoder.encode(valueToEncode, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    public static String getCurrentDirectory() {
        Path currentRelativePath = Paths.get("");
        return currentRelativePath.toAbsolutePath().toString();
    }

    public static String performRequest(RequestParams requestParams) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(requestParams.getUrl()))
                .timeout(Duration.ofMinutes(1))
                .header(Constants.API_HEADER_KEY_NAME, requestParams.getApiKey())
                .header(Constants.CONTENT_TYPE_HEADER_KEY_NAME, Constants.CONTENT_TYPE_HEADER_KEY_VALUE);
        if(Objects.equals(requestParams.getMethod(), "GET") || Objects.equals(requestParams.getMethod(), "DELETE")) {
            requestBuilder = requestBuilder.method(requestParams.getMethod(), HttpRequest.BodyPublishers.noBody());
        } else {
            requestBuilder = requestBuilder.method(requestParams.getMethod(), HttpRequest.BodyPublishers.ofString(requestParams.getBody()));
        }

        HttpResponse<String> response = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() < 300) {
            return response.body();
        } else {
            throw new Exception(response.body());
        }
    }

    public static HashMap<String, String> generateHeaders(String apiKey) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Constants.API_HEADER_KEY_NAME, apiKey);
        headers.put(Constants.CONTENT_TYPE_HEADER_KEY_NAME, Constants.CONTENT_TYPE_HEADER_KEY_NAME);

        return headers;
    }

    public static <T, U> List<U> convertArrayToList(T[] from, Function<T, U> func) {
        return Arrays.stream(from).map(func).collect(Collectors.toList());
    }

    //for arrays
    public static <T, U> U[] convertListToArray(List<T> from,
                                          Function<T, U> func,
                                          IntFunction<U[]> generator) {
        return from.stream().map(func).toArray(generator);
    }

    public static Map<String, String> parseArgs() {
        HashMap<String, String> params = new HashMap<>();

        params.put(Constants.MAP_PARAM_MODE_KEY, System.getProperty(Constants.PARAM_MODE_KEY));
        params.put(Constants.MAP_PARAM_ON_FAILURE_KEY, System.getProperty(Constants.PARAM_ON_FAILURE_KEY));
        params.put(Constants.MAP_PARAM_DIRS_TO_EXCLUDE_KEY, System.getProperty(Constants.PARAM_DIRS_TO_EXCLUDE_KEY));
        params.put(Constants.MAP_PARAM_FILES_TO_EXCLUDE_KEY, System.getProperty(Constants.PARAM_FILES_TO_EXCLUDE_KEY));
        params.put(Constants.MAP_PARAM_WORKSPACE_DIR_KEY, System.getProperty(Constants.PARAM_WORKSPACE_DIR_KEY));
        params.put(Constants.MAP_PARAM_ANALYSIS_RESULT_MAX_WAIT_KEY, System.getProperty(Constants.PARAM_ANALYSIS_RESULT_MAX_WAIT_KEY));

        return params;
    }

}
