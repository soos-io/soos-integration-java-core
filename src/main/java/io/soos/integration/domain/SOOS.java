package io.soos.integration.domain;

import io.soos.integration.domain.analysis.AnalysisResult;
import io.soos.integration.domain.analysis.AnalysisResultResponse;
import io.soos.integration.domain.analysis.AnalysisStart;
import io.soos.integration.domain.manifest.Manifest;
import io.soos.integration.domain.manifest.ManifestTypesResponse;
import io.soos.integration.domain.scan.Scan;
import io.soos.integration.domain.scan.ScanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SOOS {
    private final Logger LOG = LoggerFactory.getLogger(SOOS.class);
    protected Context context;
    protected Script script;
    protected Scan scan;
    protected Manifest manifest;
    protected AnalysisStart analysisStart;
    protected AnalysisResult analysisResult;

    public SOOS() throws Exception {
        this.context = new Context();
        this.script = new Script();

        boolean load = this.context.load();

        if (!load) {
            throw new Exception("Could not find required Environment/Script Variables.");
        }

        this.scan = new Scan(this.context);
        this.manifest = new Manifest(this.context);
        this.analysisStart = new AnalysisStart(this.context);
        this.analysisResult = new AnalysisResult(this.context);
    }


    public ManifestTypesResponse loadManifestTypes() throws Exception {
        return this.manifest.getManifestTypes();
    }

    public long sendManifestFiles(String projectId, String analysisId) throws Exception {
        return this.manifest.sendManifests(projectId, analysisId, this.script.getDirectoriesToExclude(), this.script.getFilesToExclude(), this.script.getPackageManagers());
    }

    public ScanResponse getCreateScan() throws Exception {
        return this.scan.execute();
    }

    public ScanResponse startAnalysis() throws Exception {
        ScanResponse structure = this.getCreateScan();
        String projectId = structure.getProjectId();
        String analysisId = structure.getScanId();
        LOG.info("----------------------------------");
        LOG.info("Analysis Structure Request Created");
        LOG.info("----------------------------------");
        LOG.info("Project Id: {}", projectId);
        LOG.info("Analysis Id: {}", analysisId);

        long filesProcessed = this.sendManifestFiles(projectId, analysisId);
        if(filesProcessed > 0) {
            LOG.info("You have sent a total of {} manifests to be analyzed.",
                    filesProcessed);
            LOG.info("--------------------------------------------------------");
            LOG.info("Starting Analysis");
            this.analysisStart.execute(projectId, analysisId);
            return structure;
        } else {
            throw new Exception("No Manifest files were founded on the source code path");
        }


    }

    public AnalysisResultResponse getResults(String reportStatusURL) throws Exception {
        return this.analysisResult.execute(reportStatusURL);
    }

    public Mode getMode() {
        return this.script.getMode();
    }

    public OnFailure getOnFailure() {
        return this.script.getOnFailure();
    }

    public Context getContext() {
        return this.context;
    }
}
