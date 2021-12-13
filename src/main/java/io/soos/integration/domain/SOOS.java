package io.soos.integration.domain;

import io.soos.integration.domain.analysis.AnalysisResult;
import io.soos.integration.domain.analysis.AnalysisResultResponse;
import io.soos.integration.domain.analysis.AnalysisStart;
import io.soos.integration.domain.manifest.Manifest;
import io.soos.integration.domain.manifest.ManifestTypesResponse;
import io.soos.integration.domain.structure.Structure;
import io.soos.integration.domain.structure.StructureResponse;

public class SOOS {
    protected Context context;
    protected Script script;
    protected Structure structure;
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

        this.structure = new Structure(this.context);
        this.manifest = new Manifest(this.context);
        this.analysisStart = new AnalysisStart(this.context);
        this.analysisResult = new AnalysisResult(this.context);
    }


    public ManifestTypesResponse loadManifestTypes() throws Exception {
        return this.manifest.getManifestTypes();
    }

    public long sendManifestFiles(String projectId, String analysisId) throws Exception {
        return this.manifest.sendManifests(projectId, analysisId, this.script.getDirectoriesToExclude(), this.script.getFilesToExclude());
    }

    public StructureResponse getStructure() throws Exception {
        return this.structure.execute();
    }

    public void startAnalysis(String projectId, String analysisId) throws Exception {
        this.analysisStart.execute(projectId, analysisId);
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
