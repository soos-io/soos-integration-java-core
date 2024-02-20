package io.soos.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class SoosScaWrapper {
    private static final Logger LOG = Logger.getLogger(SoosScaWrapper.class.getName());

    private Configuration config;
    private PrintStream logger;

    public SoosScaWrapper(Configuration config, PrintStream logger) {
        this.config = config;
        this.logger = logger;
    }

    public int runSca() throws IOException, InterruptedException, NpmNotFoundException {
        checkNpmInstalled(config.getNodePath());
        List<String> cliArguments = mapConfigToCLIArguments(config);

        return runSoosSca(cliArguments);
    }


    private List<String> mapConfigToCLIArguments(Configuration config) {
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
                        // if the value is a string and is empty we don't add it to the command
                        if (value instanceof String && ((String) value).isEmpty()) {
                            continue;
                        }
                        commandArguments.add(argument + "=" + value.toString() + "");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return commandArguments;
    }
    

    private String getNormalizedNodePath(String nodePath) {
        return nodePath == null || nodePath.isEmpty() ? "" : nodePath.replace("\\", "/") + "/";
    }

    private void checkNpmInstalled(String nodePath) throws IOException, InterruptedException, NpmNotFoundException {
        try {
            logger.println("Checking if Node is installed...");
            execCommand("node", new ArrayList<>(Arrays.asList("--version")));
        } catch (IOException | InterruptedException e) {
            LOG.info("Node is not installed or not found in PATH. Make sure you have Node (At least v18.18.2) and a available in your PATH.");
            throw new NpmNotFoundException("Node is not installed or not found in PATH.", e);
        }
    }
    
    private int execCommand(String command, List<String> arguments) throws IOException, InterruptedException {
        String normalizedNodePath = getNormalizedNodePath(config.getNodePath());
    
        List<String> commandParts = new ArrayList<>();
        commandParts.add(normalizedNodePath + command);
        commandParts.addAll(arguments);

        ProcessBuilder processBuilder = new ProcessBuilder(commandParts);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
    
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logger.println(line);
            }
        }
    
        int exitCode = process.waitFor();
        return exitCode;
    }

    private int runSoosSca(List<String> cliArguments) throws IOException, InterruptedException {
        String npmCommand = System.getProperty("os.name").toLowerCase().contains("win") ? "npm.cmd" : "npm";
        execCommand(npmCommand, new ArrayList<>(Arrays.asList("install", "--prefix", "./soos", "@soos-io/soos-sca")));
        List<String> fullCommand = new ArrayList<>();
        fullCommand.add("./soos/node_modules/@soos-io/soos-sca/bin/index.js");
        fullCommand.addAll(cliArguments);
        return execCommand("node", fullCommand);
    }

    static class NpmNotFoundException extends Exception {
        public NpmNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

