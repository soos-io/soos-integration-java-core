package io.soos.integration.commons;

public class Constants {
    public static final int MAX_MANIFESTS = 50;
    public static final int MIN_ANALYSIS_RESULT_POLLING_INTERVAL = 10;
    public static final int MIN_RECOMMENDED_ANALYSIS_RESULT_MAX_WAIT = 300;

    public static final int INITIATE_ANALYSIS_EXPECTED_RESPONSE_CODE = 200;

    public static String INTEGRATION_TYPE = "Plugin";
    public static String INTEGRATION_NAME = "";


    public static String API_HEADER_KEY_NAME = "x-soos-apikey";
    public static String CONTENT_TYPE_HEADER_KEY_NAME = "Content-Type";
    public static String CONTENT_LENGTH_HEADER_KEY_NAME = "content-length";
    public static String CONTENT_TYPE_HEADER_KEY_VALUE = "application/json";
    public static String CONTENT_TYPE_MULTIPART_HEADER_KEY_VALUE = "multipart/form-data";
    public static String CONTENT_LENGTH_HEADER_KEY_VALUE = "0";

    public static final int MANIFEST_API_RETRY_COUNT = 3;
    public static final int STRUCTURE_API_RETRY_COUNT = 3;
    public static final int ANALYSIS_START_API_RETRY_COUNT = 3;
    public static final int ANALYSIS_RESULT_API_RETRY_COUNT = 3;

    public static int MIN_PROJECT_NAME_LENGTH = 5;

    public static final String URL_SLASH = "/";
    public static final String URL_API_PATH = "api/";
    public static final String URL_CLIENTS_PATH = "clients/";
    public static final String URL_PROJECTS_PATH = "projects/";
    public static final String URL_ANALYSIS_PATH = "analysis/";
    public static final String URL_SCAN_TYPES_PATH = "scan-types/";
    public static final String URL_MANIFESTS_PATH = "manifests";
    public static final String URL_MANIFESTS_MAX_MANIFESTS_QUERY_PARAM = "hasMoreThanMaximumManifests";

    public static final String URL_STRUCTURE_PATH = "structure";
    public static final String URL_SCANS_PATH = "scans";
    public static final String PROPERTIES_FILE = "/project.properties";
    public static final String VERSION = "version";

    public static final String DEFAULT_ASYNC_RESULT_FILE_NAME = "soos_async.json";

    public static final String PROJECT_NAME_PATTERN = "[^a-zA-Z0-9 .._-]";
    public static final String VALID_URL_PATTERN = "(https?:\\/\\/)?([\\w\\-])+\\.{1}([a-zA-Z]{2,63})([\\/\\w-]*)*\\/?\\??([^#\\n\\r]*)?#?([^\\n\\r]*)";

    public static final String SOOS_DEFAULT_API_URL = "https://api.soos.io/api/";

    // ENV Variables
    public static final String SOOS_API_BASE_URI = "SOOS_API_BASE_URI";
    public static final String SOOS_ROOT_CODE_PATH = "SOOS_ROOT_CODE_PATH";
    public static final String SOOS_PROJECT_NAME = "SOOS_PROJECT_NAME";
    public static final String SOOS_CLIENT_ID = "SOOS_CLIENT_ID";
    public static final String SOOS_API_KEY = "SOOS_API_KEY";
    public static final String SOOS_ON_FAILURE = "SOOS_ON_FAILURE";
    public static final String SOOS_DIRS_TO_EXCLUDE = "SOOS_DIRS_TO_EXCLUDE";
    public static final String SOOS_FILES_TO_EXCLUDE = "SOOS_FILES_TO_EXCLUDE";
    public static final String SOOS_CHECKOUT_DIR = "SOOS_CHECKOUT_DIR";
    public static final String SOOS_ANALYSIS_RESULT_MAX_WAIT = "SOOS_ANALYSIS_RESULT_MAX_WAIT";
    public static final String SOOS_ANALYSIS_RESULT_POLLING_INTERVAL = "SOOS_ANALYSIS_RESULT_POLLING_INTERVAL";
    public static final String SOOS_COMMIT_HASH = "SOOS_COMMIT_HASH";
    public static final String SOOS_BRANCH_NAME = "SOOS_BRANCH_NAME";
    public static final String SOOS_BRANCH_URI = "SOOS_BRANCH_URI";
    public static final String SOOS_BUILD_VERSION = "SOOS_BUILD_VERSION";
    public static final String SOOS_BUILD_URI = "SOOS_BUILD_URI";
    public static final String SOOS_OPERATING_ENVIRONMENT = "SOOS_OPERATING_ENVIRONMENT";
    public static final String SOOS_INTEGRATION_NAME = "SOOS_INTEGRATION_NAME";

    // Params Arguments
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
    public static final String PARAM_PACKAGE_MANAGERS_KEY = "pkgm";

    public static final String MAP_PARAM_CLIENT_ID_KEY = "clientId";
    public static final String MAP_PARAM_API_KEY = "apiKey";
    public static final String MAP_PARAM_ON_FAILURE_KEY = "onFailure";
    public static final String MAP_PARAM_DIRS_TO_EXCLUDE_KEY = "dirsToExclude";
    public static final String MAP_PARAM_FILES_TO_EXCLUDE_KEY = "filesToExclude";
    public static final String MAP_PARAM_WORKSPACE_DIR_KEY = "workspaceDir";
    public static final String MAP_PARAM_ANALYSIS_RESULT_MAX_WAIT_KEY = "analysisResultMaxWait";
    public static final String MAP_PARAM_ANALYSIS_RESULT_POLLING_INTERVAL_KEY = "analysisResultPollingInterval";
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
    public static final String MAP_PARAM_PACKAGE_MANAGERS_KEY = "packageManagers";

    // Report Status
    public static final String REPORT_STATUS_FINISHED = "Finished";
    public static final String REPORT_STATUS_FAILED = "Failed";
    public static final String REPORT_STATUS_QUEUED = "Queued";
    public static final String REPORT_STATUS_FAILEDWITHISSUES = "FailedWithIssues";
    public static final String REPORT_STATUS_SUCCEDEDWITHISSUES = "SuccededWithIssues";
}
