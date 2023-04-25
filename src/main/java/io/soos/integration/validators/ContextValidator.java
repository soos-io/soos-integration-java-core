package io.soos.integration.validators;

import io.soos.integration.domain.Context;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class ContextValidator {

    public static boolean validate(Context context) {
        if (StringUtils.isEmpty(context.getBaseURI())) {
            System.err.println("Base URI is empty");
            return false;
        }

        if (!isValidUri(context.getBaseURI())) {
            System.err.println("Invalid base URI format: " + context.getBaseURI());
            return false;
        }

        if (StringUtils.isEmpty(context.getProjectName())) {
            System.err.println("Project name is empty");
            return false;
        }

        if (StringUtils.isEmpty(context.getClientId())) {
            System.err.println("Client ID is empty");
            return false;
        }

        if (StringUtils.isEmpty(context.getApiKey())) {
            System.err.println("API key is empty");
            return false;
        }

        return true;
    }

    private static boolean isValidUri(String uri) {
        try {
            new URI(uri);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    private static boolean isValidDirectory(String path) {
        File dir = new File(path);
        return dir.exists() && dir.isDirectory();
    }

}

