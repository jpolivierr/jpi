package com.appvenir.infrastructure.config;

import java.util.List;

public class CliConfig {
    private final String JavaProjectPath;
    private final String workSpaceTemplatePath;
    private final List<String> buildToolFileNames;

    private CliConfig(Builder builder)
    {
        this.JavaProjectPath = builder.JavaProjectPath;
        this.workSpaceTemplatePath = builder.workSpaceTemplatePath;
        this.buildToolFileNames = builder.buildToolFileNames;
    }

    public static class Builder {
        private String JavaProjectPath;
        private String workSpaceTemplatePath;
        private List<String> buildToolFileNames;

        public Builder setJavaProjectPath(String javaProjectPath) {
            JavaProjectPath = javaProjectPath;
            return this;
        }
        public Builder setWorkSpaceTemplatePath(String workSpaceTemplatePath) {
            this.workSpaceTemplatePath = workSpaceTemplatePath;
            return this;
        }
        public Builder setBuildToolFileNames(List<String> buildToolFileNames) {
            this.buildToolFileNames = buildToolFileNames;
            return this;
        }

        public CliConfig build()
        {
            return new CliConfig(this);
        }
    }

    public String getJavaProjectPath() {
        return JavaProjectPath;
    }

    public String getWorkspaceTemplatePath() {
        return workSpaceTemplatePath;
    }

    public List<String> getBuildToolFileNames() {
        return buildToolFileNames;
    }

}
