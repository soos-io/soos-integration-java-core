package io.soos.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SoosScaWrapper {
    private static final Logger LOG = Logger.getLogger(SoosScaWrapper.class.getName());

    private Configuration config;

    public SoosScaWrapper(Configuration config) {
        this.config = config;
    }

    public int runSca() throws IOException, InterruptedException, NpmNotFoundException {
        checkNpmInstalled(config.getNodePath());
        String cliArguments = mapConfigToCLIArguments(config);

        return runSoosSca(cliArguments);
    }


    private String mapConfigToCLIArguments(Configuration config) {
        List<String> commandArguments = new ArrayList<>();
        for (Field field : Configuration.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if ("nodePath".equals(field.getName())) {
                    // Skip the nodePath field as it's only used to execute the npm command
                    continue;
                }
                Object value = field.get(config);
                if (value != null) {
                    String argument = "--" + field.getName();
                    if (value instanceof Boolean) {
                        if ((Boolean) value) {
                            commandArguments.add(argument);
                        }
                    } else {
                        commandArguments.add(argument + "=\"" + value.toString() + "\"");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return String.join(" ", commandArguments);
    }
    

    private String getNormalizedNodePath(String nodePath) {
        return nodePath == null || nodePath.isEmpty() ? "" : nodePath.replace("\\", "/") + "/";
    }

    private void checkNpmInstalled(String nodePath) throws IOException, InterruptedException, NpmNotFoundException {
        try {
            execCommand("node", "--version");
        } catch (IOException | InterruptedException e) {
            LOG.info("npm is not installed or not found in PATH.");
            throw new NpmNotFoundException("npm is not installed or not found in PATH.", e);
        }
    }

    private int execCommand(String command, String arguments) throws IOException, InterruptedException{
        String normalizedNodePath = getNormalizedNodePath(config.getNodePath());
        ProcessBuilder processBuilder = new ProcessBuilder(normalizedNodePath + command, arguments);
        processBuilder.redirectErrorStream(true);
        LOG.info("Running command: " + normalizedNodePath +  command + " " + arguments);
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LOG.info(line);
            }
        }

        int exitCode = process.waitFor();
        LOG.info("Exit code: " + exitCode);
        return exitCode;
    }

    private int runSoosSca(String cliArguments) throws IOException, InterruptedException {
        execCommand("npm", "install --prefix ./soos @soos-io/soos-sca");
        return execCommand("node", "./soos/node_modules/@soos-io/soos-sca/bin/index.js " + cliArguments);
    }

    static class NpmNotFoundException extends Exception {
        public NpmNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

