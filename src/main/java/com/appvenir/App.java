package com.appvenir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.appvenir.core.ActiveProjectDetails;
import com.appvenir.core.ActiveProjectDetailsFactory;
import com.appvenir.core.javaFile.AccessModifier;
import com.appvenir.core.javaFile.ClassDetails;
import com.appvenir.core.javaFile.JavaFileDetails;
import com.appvenir.core.javaFile.JavaFileGenerator;
import com.appvenir.core.javaFile.PackageName;
import com.appvenir.core.javaFile.TypeVariable;
import com.appvenir.core.model.Schemas;
import com.appvenir.infrastructure.config.ConfigLoader;
import com.appvenir.infrastructure.config.SchemaPropertyLoader;
import com.appvenir.infrastructure.providers.MapperProvider;
import com.appvenir.infrastructure.system.logger.Logger;
import com.appvenir.infrastructure.system.parcers.buildTool.BuildToolFactory;
import com.appvenir.infrastructure.system.parcers.buildTool.BuildToolParser;
import com.appvenir.infrastructure.system.parcers.buildTool.MavenBuildToolParser;
import com.appvenir.infrastructure.system.parcers.file.YamlParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class App 
{
    public static void main( String[] args )
    {
        String projectRootDir = "/Users/Fred/cli/jpi";
        String configFile = "/Users/Fred/cli/jpi/config.properties";
        ConfigLoader configLoader = ConfigLoader.getInstance(configFile);

        // ActiveProjectDetails activeProjectDetails = new ActiveProjectDetails(projectRootDir, configLoader);
        ActiveProjectDetailsFactory activeProjectDetailsFactory = ActiveProjectDetailsFactory.newInstance(projectRootDir, configLoader);
        ActiveProjectDetails activeProjectDetails = activeProjectDetailsFactory.createFromProject();
        Logger.info(activeProjectDetails.toString());

        // ClassDetails demoClassDetails = new ClassDetails("demo", packageName);
        // demoClassDetails.addProperty(TypeVariable.newInstant(AccessModifier.PUBLIC, String.class, "name"));

        // JavaFileDetails DemoFileDetails = new JavaFileDetails(demoClassDetails);
        // JavaFileGenerator javaFileGenerator = new JavaFileGenerator(filePath, DemoFileDetails);
        // javaFileGenerator.execute();
        // yamlParcer();
    }

}
