package io.soos.integration.commons;

import io.soos.integration.domain.OnFailure;
import io.soos.integration.domain.RequestParams;
import io.soos.integration.domain.RequestParamsManifest;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {

    public static String UrlEncode(String valueToEncode) {
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
        if (Objects.equals(requestParams.getMethod(), "GET") || Objects.equals(requestParams.getMethod(), "DELETE")) {
            requestBuilder = requestBuilder.method(requestParams.getMethod(), HttpRequest.BodyPublishers.noBody());
        } else {
            requestBuilder = requestBuilder.method(requestParams.getMethod(),
                    HttpRequest.BodyPublishers.ofString(requestParams.getBody()));
        }

        HttpResponse<String> response = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 300) {
            return response.body();
        } else {
            throw new Exception(response.body());
        }
    }

    public static String analysisStartRequest(RequestParams requestParams) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(requestParams.getUrl()))
                .timeout(Duration.ofMinutes(1))
                .header(Constants.API_HEADER_KEY_NAME, requestParams.getApiKey())
                .header(Constants.CONTENT_TYPE_HEADER_KEY_NAME, Constants.CONTENT_TYPE_MULTIPART_HEADER_KEY_VALUE);
        if (Objects.equals(requestParams.getMethod(), "GET") || Objects.equals(requestParams.getMethod(), "DELETE")) {
            requestBuilder = requestBuilder.method(requestParams.getMethod(),
                    HttpRequest.BodyPublishers.noBody());
        } else {
            requestBuilder = requestBuilder.method(requestParams.getMethod(),
                    HttpRequest.BodyPublishers.ofString(requestParams.getBody()));
        }

        HttpResponse<String> response = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 300) {
            return response.body();
        } else {
            throw new Exception(response.body());
        }
    }

    public static String getFileContent(Path path) {
        List<String> fileContent = null;
        try {
            fileContent = Files.readAllLines(
                    path,
                    StandardCharsets.UTF_8
            );

            StringBuilder fcStringBuilder = new StringBuilder();

            for (String aLine : fileContent) {
                fcStringBuilder.append(aLine);
            }
            return fcStringBuilder.toString().trim();

        } catch (IOException e) {
            e.printStackTrace();

            return "";
        }
    }

    public static String uploadManifestFile(RequestParamsManifest requestParams) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(requestParams.getUrl());
        post.addHeader(Constants.API_HEADER_KEY_NAME, requestParams.getApiKey());
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        String checkoutDir = System.getProperty(Constants.PARAM_CHECKOUT_DIR_KEY);
        List<File> files = requestParams.getFiles().stream().map(Path::toFile).collect(Collectors.toList());
        for( int i = 0; i < files.size(); i++) {
            String suffix = i > 0 ? String.valueOf(i) : "";
            String[] splittedPath = Arrays.stream(files.get(i).getPath().replace(checkoutDir, "").split(Pattern.quote(File.separator))).filter(value -> value != null && !value.equals("")).toArray(String[]::new);
            String label = "";
            if(splittedPath.length >= 2) {
                label = String.join(File.separator, Arrays.copyOfRange(splittedPath, 0, splittedPath.length - 1));
            }
            builder.addBinaryBody("file"+suffix, files.get(i));
            builder.addTextBody("parentFolder"+suffix, label);
        }
        HttpEntity requestEntity = builder.build();

        // Create a custom response handler
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };

        post.setEntity(requestEntity);
        return httpClient.execute(post, responseHandler);


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

        params.put(Constants.MAP_PARAM_CLIENT_ID_KEY, System.getProperty(Constants.SOOS_CLIENT_ID));
        params.put(Constants.MAP_PARAM_API_KEY, System.getProperty(Constants.SOOS_API_KEY));
        params.put(Constants.MAP_PARAM_MODE_KEY, System.getProperty(Constants.PARAM_MODE_KEY));
        params.put(Constants.MAP_PARAM_ON_FAILURE_KEY, System.getProperty(Constants.PARAM_ON_FAILURE_KEY));
        params.put(Constants.MAP_PARAM_DIRS_TO_EXCLUDE_KEY, System.getProperty(Constants.PARAM_DIRS_TO_EXCLUDE_KEY));
        params.put(Constants.MAP_PARAM_FILES_TO_EXCLUDE_KEY, System.getProperty(Constants.PARAM_FILES_TO_EXCLUDE_KEY));
        params.put(Constants.MAP_PARAM_WORKSPACE_DIR_KEY, System.getProperty(Constants.PARAM_WORKSPACE_DIR_KEY));
        params.put(Constants.MAP_PARAM_ANALYSIS_RESULT_MAX_WAIT_KEY, System.getProperty(Constants.PARAM_ANALYSIS_RESULT_MAX_WAIT_KEY));
        params.put(Constants.MAP_PARAM_ANALYSIS_RESULT_POLLING_INTERVAL_KEY, System.getProperty(Constants.PARAM_ANALYSIS_RESULT_POLLING_INTERVAL_KEY));
        params.put(Constants.MAP_PARAM_API_BASE_URI_KEY, System.getProperty(Constants.PARAM_API_BASE_URI_KEY));
        params.put(Constants.MAP_PARAM_CHECKOUT_DIR_KEY, System.getProperty(Constants.PARAM_CHECKOUT_DIR_KEY));
        params.put(Constants.MAP_PARAM_PROJECT_NAME_KEY, System.getProperty(Constants.PARAM_PROJECT_NAME_KEY));
        params.put(Constants.MAP_PARAM_COMMIT_HASH_KEY, System.getProperty(Constants.PARAM_COMMIT_HASH_KEY));
        params.put(Constants.MAP_PARAM_BRANCH_NAME_KEY, System.getProperty(Constants.PARAM_BRANCH_NAME_KEY));
        params.put(Constants.MAP_PARAM_BRANCH_URI_KEY, System.getProperty(Constants.PARAM_BRANCH_URI_KEY));
        params.put(Constants.MAP_PARAM_BUILD_VERSION_KEY, System.getProperty(Constants.PARAM_BUILD_VERSION_KEY));
        params.put(Constants.MAP_PARAM_BUILD_URI_KEY, System.getProperty(Constants.PARAM_BUILD_URI_KEY));
        params.put(Constants.MAP_PARAM_OPERATING_ENVIRONMENT_KEY, System.getProperty(Constants.PARAM_OPERATING_ENVIRONMENT_KEY));
        params.put(Constants.MAP_PARAM_INTEGRATION_NAME_KEY, System.getProperty(Constants.PARAM_INTEGRATION_NAME_KEY));
        params.put(Constants.MAP_PARAM_PACKAGE_MANAGERS_KEY, System.getProperty(Constants.PARAM_PACKAGE_MANAGERS_KEY));

        return params;
    }

    public static Map<String, String> parseEnvVariables() {
        HashMap<String, String> envVariables = new HashMap<>();

        envVariables.put(Constants.MAP_PARAM_MODE_KEY, System.getenv(Constants.SOOS_MODE));
        envVariables.put(Constants.MAP_PARAM_ON_FAILURE_KEY, System.getenv(Constants.SOOS_ON_FAILURE));
        envVariables.put(Constants.MAP_PARAM_DIRS_TO_EXCLUDE_KEY, System.getenv(Constants.SOOS_DIRS_TO_EXCLUDE));
        envVariables.put(Constants.MAP_PARAM_FILES_TO_EXCLUDE_KEY, System.getenv(Constants.SOOS_FILES_TO_EXCLUDE));
        envVariables.put(Constants.MAP_PARAM_ANALYSIS_RESULT_MAX_WAIT_KEY, System.getenv(Constants.SOOS_ANALYSIS_RESULT_MAX_WAIT));
        envVariables.put(Constants.MAP_PARAM_ANALYSIS_RESULT_POLLING_INTERVAL_KEY, System.getenv(Constants.SOOS_ANALYSIS_RESULT_POLLING_INTERVAL));
        envVariables.put(Constants.MAP_PARAM_API_BASE_URI_KEY, System.getenv(Constants.SOOS_API_BASE_URI));
        envVariables.put(Constants.MAP_PARAM_CHECKOUT_DIR_KEY, System.getenv(Constants.SOOS_CHECKOUT_DIR));
        envVariables.put(Constants.MAP_PARAM_PROJECT_NAME_KEY, System.getenv(Constants.SOOS_PROJECT_NAME));
        envVariables.put(Constants.MAP_PARAM_COMMIT_HASH_KEY, System.getenv(Constants.SOOS_COMMIT_HASH));
        envVariables.put(Constants.MAP_PARAM_BRANCH_NAME_KEY, System.getenv(Constants.SOOS_BRANCH_NAME));
        envVariables.put(Constants.MAP_PARAM_BRANCH_URI_KEY, System.getenv(Constants.SOOS_BRANCH_URI));
        envVariables.put(Constants.MAP_PARAM_BUILD_VERSION_KEY, System.getenv(Constants.SOOS_BUILD_VERSION));
        envVariables.put(Constants.MAP_PARAM_BUILD_URI_KEY, System.getenv(Constants.SOOS_BUILD_URI));
        envVariables.put(Constants.MAP_PARAM_OPERATING_ENVIRONMENT_KEY, System.getenv(Constants.SOOS_OPERATING_ENVIRONMENT));
        envVariables.put(Constants.MAP_PARAM_INTEGRATION_NAME_KEY, System.getenv(Constants.SOOS_INTEGRATION_NAME));
        envVariables.put(Constants.MAP_PARAM_CLIENT_ID_KEY, System.getenv(Constants.SOOS_CLIENT_ID));
        envVariables.put(Constants.MAP_PARAM_API_KEY, System.getenv(Constants.SOOS_API_KEY));

        return envVariables;
    }

    public static boolean shouldFaildBuild(OnFailure onFailure, String status){
        return  onFailure == OnFailure.FAIL_THE_BUILD && (status.contains(Constants.REPORT_STATUS_FAILEDWITHISSUES) || status.contains(Constants.REPORT_STATUS_FAILED));
    }

    public static boolean manifestFileIsValid(File pathName, String searchPattern, List<File> dirsToExclude, List<File> filesToExclude) {
        if(searchPattern.contains("*")) {
            searchPattern = searchPattern.replace("*",".*");
        }
        Pattern pattern = Pattern.compile(searchPattern.toLowerCase());
        Matcher matcher = pattern.matcher(pathName.getName().toLowerCase());
        String checkoutDir = System.getProperty(Constants.PARAM_CHECKOUT_DIR_KEY);
        String relativeFilePath = pathName.getAbsolutePath().replace(checkoutDir, "");
        boolean isInDirsToExclude = dirsToExclude.stream().anyMatch(file -> Arrays.asList(relativeFilePath.split(Pattern.quote(File.separator))).contains(file.getPath()));
        boolean isFileToExclude = filesToExclude.stream().anyMatch(file -> file.getName().toLowerCase().contains(pathName.getName().toLowerCase()));
        return !isInDirsToExclude &&
                !isFileToExclude &&
                matcher.find();
    }

}
