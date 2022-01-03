package io.soos.integration;

import io.soos.integration.domain.SOOS;
import io.soos.integration.domain.analysis.AnalysisResultResponse;
import io.soos.integration.domain.structure.StructureResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SOOSApplication {
    private static final Logger LOG = LoggerFactory.getLogger(SOOSApplication.class);

    public static void main(String[] args) {
        try {

            SOOS soos = new SOOS();
            StructureResponse structure = null;
            AnalysisResultResponse result = null;
            LOG.info("--------------------------------------------");
            switch (soos.getMode()) {
                case RUN_AND_WAIT:
                    LOG.info("Run and Wait Scan");
                    LOG.info("--------------------------------------------");
                    structure = soos.startAnalysis();
                    LOG.info("Analysis request is running");
                    result = soos.getResults(structure.getReportStatusUrl());
                    LOG.info("Scan Analysis success. To see the results go to: {}", result.getReportUrl());
                    break;
                case ASYNC_INIT:
                    LOG.info("Async Init Scan");
                    LOG.info("--------------------------------------------");
                    structure = soos.startAnalysis();
                    LOG.info("Analysis request is running, access the report status using this link: {}", structure.getReportStatusUrl());
                    break;
                case ASYNC_RESULT:
                    LOG.info("Async Result Scan");
                    LOG.info("--------------------------------------------");
                    String reportStatusUrl = args[0];
                    LOG.info("Checking Scan Status from: {}", reportStatusUrl);
                    result = soos.getResults(reportStatusUrl);

                    LOG.info("Scan Finished success. To see the results go to: {}", result.getReportUrl());
                    break;
                default:
                    throw new Exception("Invalid SCA Mode");
            }
            System.exit(0);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            LOG.error("Scan Analysis Failed");
            System.exit(1);
        }
    }
}
