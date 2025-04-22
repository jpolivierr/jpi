package com.appvenir.api.springInitializer;

import java.util.List;
public record SpringProjectDetails(
    String type,
    String language,
    String bootVersion,
    String groupId,
    String artifactId,
    String name,
    String packageName,
    String packaging,
    String javaVersion,
    List<String> dependencies,
    String baseDir
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String type;
        private String language;
        private String bootVersion;
        private String groupId;
        private String artifactId;
        private String name;
        private String packageName;
        private String packaging;
        private String javaVersion;
        private List<String> dependencies;
        private String baseDir;

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder bootVersion(String bootVersion) {
            this.bootVersion = bootVersion;
            return this;
        }

        public Builder groupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder artifactId(String artifactId) {
            this.artifactId = artifactId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder packaging(String packaging) {
            this.packaging = packaging;
            return this;
        }

        public Builder javaVersion(String javaVersion) {
            this.javaVersion = javaVersion;
            return this;
        }

        public Builder dependencies(List<String> dependencies) {
            this.dependencies = dependencies;
            return this;
        }

        public Builder baseDir(String baseDir) {
            this.baseDir = baseDir;
            return this;
        }

        public SpringProjectDetails build() {
            return new SpringProjectDetails(
                type,
                language,
                bootVersion,
                groupId,
                artifactId,
                name,
                packageName,
                packaging,
                javaVersion,
                dependencies,
                baseDir
            );
        }
    }
}

