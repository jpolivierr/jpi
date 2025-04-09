package com.appvenir.core;

public class ActiveProjectDetails {
    private final String rootDir;
    private final String sourceDir;
    private final String buildToolFileName;
    private final String rootPackageName;

    private ActiveProjectDetails(Builder builder)
    {
        this.rootDir = builder.rootDir;
        this.sourceDir = builder.sourceDir;
        this.buildToolFileName = builder.buildToolFileName;
        this.rootPackageName = builder.rootPackageName;
    }

    public static class Builder {
        private String rootDir;
        private String sourceDir;
        private String buildToolFileName;
        private String rootPackageName;

        public Builder setRootDir(String rootDir) {
            this.rootDir = rootDir;
            return this;
        }

        public Builder setSourceDir(String sourceDir) {
            this.sourceDir = sourceDir;
            return this;
        }

        public Builder setBuildToolFileName(String buildToolFileName) {
            this.buildToolFileName = buildToolFileName;
            return this;
        }

        public Builder setRootPackageName(String rootPackageName) {
            this.rootPackageName = rootPackageName;
            return this;
        }

        public ActiveProjectDetails build()
        {
            return new ActiveProjectDetails(this);
        }
    }

    public String getRootDir() {
        return rootDir;
    }

    public String getSourceDir() {
        return sourceDir;
    }

    public String getBuildToolFileName() {
        return buildToolFileName;
    }

    public String getRootPackageName() {
        return rootPackageName;
    }

    @Override
    public String toString() {
        return "ActiveProjectDetails [rootDir=" + rootDir + ", sourceDir=" + sourceDir + ", buildToolFileName="
                + buildToolFileName + ", rootPackageName=" + rootPackageName + "]";
    }

    

    
}
