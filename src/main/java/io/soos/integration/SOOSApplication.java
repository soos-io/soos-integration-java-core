package io.soos.integration;

import io.soos.integration.domain.SOOS;
import io.soos.integration.domain.analysis.AnalysisResultResponse;
import io.soos.integration.domain.manifest.ManifestTypesResponse;
import io.soos.integration.domain.structure.StructureResponse;

public class SOOSApplication {
    public static void main(String[] args) {
        try {
            SOOS soos = new SOOS();

            StructureResponse structure = soos.getStructure();
            System.out.println(structure.toString());

            long filesProcessed = soos.sendManifestFiles(structure.getProjectId(), structure.getAnalysisId());
            System.out.println("File processed: " +filesProcessed);

            if(filesProcessed > 0) {
                soos.startAnalysis(structure.getProjectId(), structure.getAnalysisId());

                switch (soos.getMode()) {
                    case RUN_AND_WAIT:
                        AnalysisResultResponse results = soos.getResults(structure.getReportStatusUrl());

                        System.out.println(results.toString());
                        break;
                    case ASYNC_INIT:
                        break;
                    case ASYNC_RESULT:
                        break;
                }

            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
