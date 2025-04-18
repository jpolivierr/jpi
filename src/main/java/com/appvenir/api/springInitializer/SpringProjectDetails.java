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
    List<String> dependencies
){}
