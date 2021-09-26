package io.soos.integration.commons;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final int MIN_ANALYSIS_RESULT_POLLING_INTERVAL = 10;
    public static final int MIN_RECOMMENDED_ANALYSIS_RESULT_MAX_WAIT = 300;

    public static final int INITIATE_ANALYSIS_EXPECTED_RESPONSE_CODE = 200;

    public static String INTEGRATION_TYPE = "CI";

    public static String API_HEADER_KEY_NAME = "x-soos-apikey";
    public static String CONTENT_TYPE_HEADER_KEY_NAME = "Content-Type";
    public static String CONTENT_TYPE_HEADER_KEY_VALUE = "application/json";

    public static final int MANIFEST_API_RETRY_COUNT = 3;
    public static final int STRUCTURE_API_RETRY_COUNT = 3;
    public static final int ANALYSIS_START_API_RETRY_COUNT = 3;
    public static final int ANALYSIS_RESULT_API_RETRY_COUNT = 3;

    public static int MIN_PROJECT_NAME_LENGTH = 5;

    public static final String URL_SLASH = "/";
    public static final String URL_CLIENTS_PATH = "clients/";
    public static final String URL_PROJECTS_PATH = "projects/";
    public static final String URL_ANALYSIS_PATH = "analysis/";
    public static final String URL_MANIFESTS_PATH = "manifests";
    public static final String URL_STRUCTURE_PATH = "structure";

    public static final String DEFAULT_ASYNC_RESULT_FILE_NAME = "soos_async.json";

    public static final String PROJECT_NAME_PATTERN = "[^a-zA-Z0-9 .._-]";
    public static final String VALID_URL_PATTERN = "(https?:\\/\\/)?([\\w\\-])+\\.{1}([a-zA-Z]{2,63})([\\/\\w-]*)*\\/?\\??([^#\\n\\r]*)?#?([^\\n\\r]*)";

    public static final String SOOS_DEFAULT_API_URL = "https://api.soos.io";

    // ENV Variables
    public static final String SOOS_API_BASE_URI = "SOOS_API_BASE_URI";
    public static final String SOOS_ROOT_CODE_PATH = "SOOS_ROOT_CODE_PATH";
    public static final String SOOS_PROJECT_NAME = "SOOS_PROJECT_NAME";
    public static final String SOOS_CLIENT_ID = "SOOS_CLIENT_ID";
    public static final String SOOS_API_KEY = "SOOS_API_KEY";

    // Params Arguments
    public static final String PARAM_MODE_KEY = "m";
    public static final String PARAM_ON_FAILURE_KEY = "of";
    public static final String PARAM_DIRS_TO_EXCLUDE_KEY = "dte";
    public static final String PARAM_FILES_TO_EXCLUDE_KEY = "fte";
    public static final String PARAM_WORKSPACE_DIR_KEY = "wd";
    public static final String PARAM_ANALYSIS_RESULT_MAX_WAIT_KEY = "armw";
    public static final String PARAM_ANALYSIS_RESULT_POLLING_INTERVAL_KEY = "arpi";
    public static final String PARAM_API_BASE_URI_KEY = "buri";
    public static final String PARAM_CHECKOUT_DIR_KEY = "scp";
    public static final String PARAM_PROJECT_NAME_KEY = "pn";
    public static final String PARAM_COMMIT_HASH_KEY = "ch";
    public static final String PARAM_BRANCH_NAME_KEY = "brn";
    public static final String PARAM_BRANCH_URI_KEY = "bruri";
    public static final String PARAM_BUILD_VERSION_KEY = "bldver";
    public static final String PARAM_BUILD_URI_KEY = "blduri";
    public static final String PARAM_OPERATING_ENVIRONMENT_KEY = "oe";
    public static final String PARAM_INTEGRATION_NAME_KEY = "intn";

    public static final String MAP_PARAM_MODE_KEY = "mode";
    public static final String MAP_PARAM_ON_FAILURE_KEY = "onFailure";
    public static final String MAP_PARAM_DIRS_TO_EXCLUDE_KEY = "dirsToExclude";
    public static final String MAP_PARAM_FILES_TO_EXCLUDE_KEY = "filesToExclude";
    public static final String MAP_PARAM_WORKSPACE_DIR_KEY = "workspaceDir";
    public static final String MAP_PARAM_ANALYSIS_RESULT_MAX_WAIT_KEY = "analysisResultMaxWait";
    public static final String MAP_PARAM_ANALYSIS_RESULT_POLLING_INTERVAL_KEY = "analysisResultPoolingInterval";
    public static final String MAP_PARAM_API_BASE_URI_KEY = "apiBaseURI";
    public static final String MAP_PARAM_CHECKOUT_DIR_KEY = "checkoutDirectory";
    public static final String MAP_PARAM_PROJECT_NAME_KEY = "projectName";
    public static final String MAP_PARAM_COMMIT_HASH_KEY = "commitHash";
    public static final String MAP_PARAM_BRANCH_NAME_KEY = "branchName";
    public static final String MAP_PARAM_BRANCH_URI_KEY = "branchURI";
    public static final String MAP_PARAM_BUILD_VERSION_KEY = "buildVersion";
    public static final String MAP_PARAM_BUILD_URI_KEY = "buildURI";
    public static final String MAP_PARAM_OPERATING_ENVIRONMENT_KEY = "operatingEnvironment";
    public static final String MAP_PARAM_INTEGRATION_NAME_KEY = "integrationName";
}