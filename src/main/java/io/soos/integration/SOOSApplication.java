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
            switch (soos.getMode()) {
                case RUN_AND_WAIT:
                    LOG.info("Run and Wait Scan");
                    LOG.info("--------------------------------------------");
                    scan = soos.startAnalysis();
                    LOG.info("Analysis request is running");
                    result = soos.getResults(scan.getScanStatusUrl());
                    if(Utils.ShouldFaildBuild(soos.getOnFailure(), result.getStatus())){
                        LOG.info("Scan failed with status {}", result.getStatus());
                        LOG.info("Vulnerabilities: {}, Violations: {}", result.getVulnerabilities(), result.getViolations());
                        System.exit(0);
                    }
                    LOG.info("Scan analysis finished with status {}. To see the results go to: {}", result.getStatus(), result.getScanUrl());
                    LOG.info("Vulnerabilities: {}, Violations: {}", result.getVulnerabilities(), result.getViolations());
                    break;
                case ASYNC_INIT:
                    LOG.info("Async Init Scan");
                    LOG.info("--------------------------------------------");
                    scan = soos.startAnalysis();
                    LOG.info("Analysis request is running, access the report status using this link: {}", scan.getScanStatusUrl());
                    if(Utils.ShouldFaildBuild(soos.getOnFailure(), result.getStatus())){
                        LOG.info("Scan failed with status {}", result.getStatus());
                        LOG.info("Vulnerabilities: {}, Violations: {}", result.getVulnerabilities(), result.getViolations());
                        System.exit(0);
                    }
                    LOG.info("Vulnerabilities: {}, Violations: {}", result.getVulnerabilities(), result.getViolations());
                    break;
                case ASYNC_RESULT:
                    LOG.info("Async Result Scan");
                    LOG.info("--------------------------------------------");
                    String reportStatusUrl = args[0];
                    LOG.info("Checking Scan Status from: {}", reportStatusUrl);
                    result = soos.getResults(reportStatusUrl);
                    if(Utils.ShouldFaildBuild(soos.getOnFailure(), result.getStatus())){
                        LOG.info("Scan failed with status {}", result.getStatus());
                        LOG.info("Vulnerabilities: {}, Violations: {}", result.getVulnerabilities(), result.getViolations());
                        System.exit(0);
                    }
                    LOG.info("Scan analysis finished successfully. To see the results go to: {}", result.getScanUrl());
                    LOG.info("Vulnerabilities: {}, Violations: {}", result.getVulnerabilities(), result.getViolations());
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
