package com.appvenir.core.model;

import java.nio.file.Paths;

import com.appvenir.core.valueObjects.PackageName;
import com.appvenir.utils.StringUtils;

public class ActiveProjectDetails {
    private final String rootPath;
    private final String sourcePath;
    private final String buildToolFileName;
    private final PackageName rootPackageName;
    private final String appRootPath;

    private ActiveProjectDetails(Builder builder)
    {
        this.rootPath = builder.rootPath;
        this.sourcePath = builder.sourcePath;
        this.buildToolFileName = builder.buildToolFileName;
        this.rootPackageName = builder.rootPackageName;
        this.appRootPath = Paths.get(rootPath + sourcePath + StringUtils.convertPackagetoPath(rootPackageName)).normalize().toString();
    }

    public static class Builder {
        private String rootPath;
        private String sourcePath;
        private String buildToolFileName;
        private PackageName rootPackageName;

        public Builder setRootPath(String rootPath) {
            this.rootPath = rootPath;
            return this;
        }

        public Builder setSourcePath(String sourcePath) {
            this.sourcePath = sourcePath;
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

    public String getRootPath() {
        return rootPath;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getBuildToolFileName() {
        return buildToolFileName;
    }

    public PackageName getRootPackageName() {
        return rootPackageName;
    }

    public String getAppRootPath() {
        return appRootPath;
    }

    @Override
    public String toString() {
        return "ActiveProjectDetails [rootPath=" + rootPath + ", sourcePath=" + sourcePath + ", buildToolFileName="
                + buildToolFileName + ", rootPackageName=" + rootPackageName + "]";
    }

}
