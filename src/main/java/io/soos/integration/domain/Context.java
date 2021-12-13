package io.soos.integration.domain;

import io.soos.integration.commons.Constants;
import io.soos.integration.commons.Utils;
import io.soos.integration.validators.ContextValidator;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Context {
    protected String baseURI;
    protected String sourceCodePath;
    protected String projectName;
    protected String clientId;
    protected String apiKey;
    protected String commitHash;
    protected String branchName;
    protected String branchURI;
    protected String buildVersion;
    protected String buildURI;
    protected String operatingEnvironment;
    protected String integrationName;
    protected String integrationType;
    protected String scriptVersion;
    protected int analysisResultMaxWait;
    protected int analysisResultPoolInterval;

    private final Map<String, String> params;

    private final Logger LOG = LoggerFactory.getLogger(Context.class);

    public Context() {
        this.scriptVersion = getVersionFromProperties();
        this.integrationType = Constants.INTEGRATION_TYPE;
        this.integrationName = Constants.INTEGRATION_NAME;
        this.params = Utils.parseArgs();
        this.analysisResultMaxWait = 300;
        this.analysisResultPoolInterval = 10;
    }

    public String getBaseURI() {
        return baseURI;
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    public String getSourceCodePath() {
        return sourceCodePath;
    }

    public void setSourceCodePath(String sourceCodePath) {
        this.sourceCodePath = sourceCodePath;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchURI() {
        return branchURI;
    }

    public void setBranchURI(String branchURI) {
        this.branchURI = branchURI;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getBuildURI() {
        return buildURI;
    }

    public void setBuildURI(String buildURI) {
        this.buildURI = buildURI;
    }

    public String getOperatingEnvironment() {
        return operatingEnvironment;
    }

    public void setOperatingEnvironment(String operatingEnvironment) {
        this.operatingEnvironment = operatingEnvironment;
    }

    public String getIntegrationName() {
        return integrationName;
    }

    public void setIntegrationName(String integrationName) {
        this.integrationName = integrationName;
    }

    public String getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }

    public int getAnalysisResultMaxWait() {
        return analysisResultMaxWait;
    }

    public void setAnalysisResultMaxWait(int analysisResultMaxWait) {
        this.analysisResultMaxWait = analysisResultMaxWait;
    }

    public int getAnalysisResultPoolInterval() {
        return analysisResultPoolInterval;
    }

    public void setAnalysisResultPoolInterval(int analysisResultPoolInterval) {
        this.analysisResultPoolInterval = analysisResultPoolInterval;
    }

    public String getScriptVersion() { return scriptVersion; }

    public void setScriptVersion(String scriptVersion) { this.scriptVersion = scriptVersion; }

    private void reset() {
        this.baseURI = Constants.SOOS_DEFAULT_API_URL;
        this.sourceCodePath = null;
        this.projectName = null;
        this.clientId = null;
        this.apiKey = null;
    }

    private void loadFromEnvVariables() {
        this.reset();
        String baseURIEnvVar = System.getenv(Constants.SOOS_API_BASE_URI);
        if(!StringUtils.isEmpty(baseURIEnvVar)){
            this.baseURI = baseURIEnvVar;
        }
        this.sourceCodePath = System.getenv(Constants.SOOS_ROOT_CODE_PATH);
        this.projectName = System.getenv(Constants.SOOS_PROJECT_NAME);
        this.clientId = System.getenv(Constants.SOOS_CLIENT_ID);
        this.apiKey = System.getenv(Constants.SOOS_API_KEY);
    }

    private void loadFromArgs() {
        if(StringUtils.isEmpty(this.clientId)){
            this.clientId = this.params.get(Constants.MAP_PARAM_CLIENT_ID_KEY);
        }
        if(StringUtils.isEmpty(this.apiKey)){
            this.apiKey = this.params.get(Constants.MAP_PARAM_API_KEY);
        }

        String baseURIParam = this.params.get(Constants.MAP_PARAM_API_BASE_URI_KEY);
        if(!StringUtils.isEmpty(baseURIParam)) {
            this.baseURI = baseURIParam;
        }

        if(StringUtils.isEmpty(this.sourceCodePath)) {
            this.sourceCodePath = this.params.get(Constants.MAP_PARAM_CHECKOUT_DIR_KEY);
        }

        if(StringUtils.isEmpty(this.projectName)) {
            this.projectName = this.params.get(Constants.MAP_PARAM_PROJECT_NAME_KEY);
        }

        // Special Context - loads from script arguments only
        this.loadPropsFromParams();


    }

    private void loadPropsFromParams() {
        this.baseURI = this.loadProperty(this.baseURI, Constants.MAP_PARAM_API_BASE_URI_KEY);
        this.commitHash = this.loadProperty(this.commitHash, Constants.MAP_PARAM_COMMIT_HASH_KEY);
        this.branchName = this.loadProperty(this.branchName, Constants.MAP_PARAM_BRANCH_NAME_KEY);
        this.branchURI = this.loadProperty(this.branchURI, Constants.MAP_PARAM_BRANCH_URI_KEY);
        this.buildVersion = this.loadProperty(this.buildVersion, Constants.MAP_PARAM_BUILD_VERSION_KEY);
        this.buildURI = this.loadProperty(this.buildURI, Constants.MAP_PARAM_BUILD_URI_KEY);
        this.operatingEnvironment = this.loadProperty(this.operatingEnvironment, Constants.MAP_PARAM_OPERATING_ENVIRONMENT_KEY);
        this.integrationName = this.loadProperty(this.integrationName, Constants.MAP_PARAM_INTEGRATION_NAME_KEY);
        this.analysisResultMaxWait = this.loadIntProperty(this.analysisResultMaxWait, Constants.MAP_PARAM_ANALYSIS_RESULT_MAX_WAIT_KEY);
        this.analysisResultPoolInterval = this.loadIntProperty(this.analysisResultPoolInterval, Constants.MAP_PARAM_ANALYSIS_RESULT_POLLING_INTERVAL_KEY);
    }

    private String loadProperty(String property, String paramMapKey) {
        String paramValue = this.params.get(paramMapKey);

        if(!StringUtils.isEmpty(paramValue)) {
            return paramValue;
        }
        return property;
    }

    private Integer loadIntProperty(int property, String paramMapKey) {
        String paramValue = this.params.get(paramMapKey);

        if(!StringUtils.isEmpty(paramValue)) {
            return Integer.parseInt(paramValue);
        }
        return property;
    }

    public boolean load() {
        this.loadFromEnvVariables();

        if(!ContextValidator.validate(this)) {
            this.loadFromArgs();

            return ContextValidator.validate(this);
        }

        return true;

    }

    private String getVersionFromProperties(){

        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getResourceAsStream("/project.properties"));
        } catch (IOException e) {
            LOG.error("Cannot read file 'project.properties'", e);
        }
        return prop.getProperty("version");

        /*MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = null;
        try {
            model = reader.read(new FileReader("pom.xml"));
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return model.getVersion();*/
    }
}
