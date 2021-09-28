package io.soos.integration.commons;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.util.List;

public class ManifestFilesFilter implements FileFilter {

    protected String regex;
    protected List<File> dirsToExclude;
    protected List<File> filesToExclude;

    public ManifestFilesFilter(String pattern, List<File> dirsToExclude, List<File> filesToExclude) {
        if(pattern.startsWith("*")) {
            pattern = ".".concat(pattern);
        }
        this.regex = pattern.concat("$");
        this.dirsToExclude = dirsToExclude;
        this.filesToExclude = filesToExclude;
    }

    @Override
    public boolean accept(File pathname) {
        return !this.dirsToExclude.contains(pathname.getParentFile()) &&
                !this.filesToExclude.contains(pathname) &&
                pathname.getName().matches(this.regex);
    }
}
