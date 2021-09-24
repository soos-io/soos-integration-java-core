package io.soos.integration.api;

import io.soos.integration.builders.AnalysisStartAPIURLBuilder;
import io.soos.integration.builders.ManifestAPIURLBuilder;
import io.soos.integration.builders.StructureAPIURLBuilder;
import io.soos.integration.commons.Constants;
import io.soos.integration.commons.Utils;
import io.soos.integration.domain.SOOS;
import io.soos.integration.domain.SOOSStructureAPIBody;
import io.soos.integration.exceptions.AnalysisFailureException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SOOSAPI {
    protected String baseURI;
    protected String clientId;
    protected String apiKey;

    public SOOSAPI() {

    }

    public SOOSAPI(String baseURI, String clientId, String apiKey) {
        this.baseURI = baseURI;
        this.clientId = clientId;
        this.apiKey = apiKey;
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.API_HEADER_KEY_NAME, this.apiKey);

        return headers;
    }

    private String generateManifestApiUrl(String projectId, String analysisId, String manifestLabel, String manifestName){

        // Convert manifest name to a URL-Encoded version
        String manifestLabelForUrl = Utils.UrlEncode(manifestLabel);
        String manifestNameForUrl = Utils.UrlEncode(manifestName);

        ManifestAPIURLBuilder apiUrlBuilder = new ManifestAPIURLBuilder(
                this.baseURI,
                this.clientId,
                projectId,
                analysisId,
                manifestLabelForUrl,
                manifestNameForUrl,
                "0"
        );

        return apiUrlBuilder.buildURL();
    }

    private String generateInitiateAnalysisApiUrl(String projectId, String analysisId){
        AnalysisStartAPIURLBuilder apiUrl = new AnalysisStartAPIURLBuilder(this.baseURI, this.clientId, projectId, analysisId);

        return apiUrl.buildURL();
    }

    private String generateStructureApiUrl(){
        StructureAPIURLBuilder apiUrl = new StructureAPIURLBuilder(this.baseURI, this.clientId);

        return apiUrl.buildURL();
    }

    public void sendManifests(String projectId, String analysisId, List<String> dirsToExclude, List<String> filesToExclude) {

    }

    public void execStructureApi(SOOSStructureAPIBody soos) throws AnalysisFailureException {
        String url = this.generateStructureApiUrl();

    }


}
