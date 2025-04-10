package com.appvenir.core;

import java.nio.file.Paths;

import com.appvenir.utils.StringUtils;

public class ActiveProjectDetails {
    private final String rootDir;
    private final String sourceDir;
    private final String buildToolFileName;
    private final PackageName rootPackageName;
    private final String appRootDir;

    private ActiveProjectDetails(Builder builder)
    {
        this.rootDir = builder.rootDir;
        this.sourceDir = builder.sourceDir;
        this.buildToolFileName = builder.buildToolFileName;
        this.rootPackageName = builder.rootPackageName;
        this.appRootDir = Paths.get(rootDir + sourceDir + StringUtils.convertPackagetoPath(rootPackageName)).normalize().toString();
    }

    public static class Builder {
        private String rootDir;
        private String sourceDir;
        private String buildToolFileName;
        private PackageName rootPackageName;

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

        public Builder setRootPackageName(PackageName rootPackageName) {
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

    public PackageName getRootPackageName() {
        return rootPackageName;
    }

    public String getAppRootDir() {
        return appRootDir;
    }

    @Override
    public String toString() {
        return "ActiveProjectDetails [rootDir=" + rootDir + ", sourceDir=" + sourceDir + ", buildToolFileName="
                + buildToolFileName + ", rootPackageName=" + rootPackageName + "]";
    }

}
