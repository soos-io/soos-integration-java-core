package io.soos.integration;

import io.soos.integration.commons.Utils;
import io.soos.integration.domain.SOOS;
import io.soos.integration.domain.analysis.AnalysisResultResponse;
import io.soos.integration.domain.scan.ScanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SOOSApplication {
    private static final Logger LOG = LoggerFactory.getLogger(SOOSApplication.class);

    public static void main(String[] args) {
        try {
            SOOS soos = new SOOS();
            ScanResponse scan = null;
            AnalysisResultResponse result = null;
            LOG.info("--------------------------------------------");
            LOG.info("Run and Wait SOOS Scan");
            LOG.info("--------------------------------------------");
            scan = soos.startAnalysis();
            LOG.info("Analysis request is running");
            result = soos.getResults(scan.getScanStatusUrl());
            if(Utils.shouldFaildBuild(soos.getOnFailure(), result.getStatus())){
                LOG.info("Scan failed with status {}", result.getStatus());
                LOG.info("Vulnerabilities: {}, Violations: {}", result.getVulnerabilities(), result.getViolations());
                System.exit(0);
            }
            LOG.info("Scan analysis finished with status {}. To see the results go to: {}", result.getStatus(), result.getScanUrl());
            LOG.info("Vulnerabilities: {}, Violations: {}", result.getVulnerabilities(), result.getViolations());
            System.exit(0);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            LOG.error("Scan Analysis Failed");
            System.exit(1);
        }
    }
}
