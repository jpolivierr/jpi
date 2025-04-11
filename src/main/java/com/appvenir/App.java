package com.appvenir;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.appvenir.core.ActiveProjectDetails;
import com.appvenir.core.ActiveProjectDetailsFactory;
import com.appvenir.core.action.CreateFileStructureAction;
import com.appvenir.core.javaFile.AccessModifier;
import com.appvenir.core.javaFile.ClassDetails;
import com.appvenir.core.javaFile.JavaFileDetails;
import com.appvenir.core.javaFile.JavaFileGenerator;
import com.appvenir.core.javaFile.PackageName;
import com.appvenir.core.javaFile.TypeVariable;
import com.appvenir.core.model.DirSchema;
import com.appvenir.core.model.Schemas;
import com.appvenir.infrastructure.config.ConfigLoader;
import com.appvenir.infrastructure.config.SchemaPropertyLoader;
import com.appvenir.infrastructure.providers.MapperProvider;
import com.appvenir.infrastructure.system.logger.Logger;
import com.appvenir.infrastructure.system.parcers.buildTool.BuildToolFactory;
import com.appvenir.infrastructure.system.parcers.buildTool.BuildToolParser;
import com.appvenir.infrastructure.system.parcers.buildTool.MavenBuildToolParser;
import com.appvenir.infrastructure.system.parcers.file.YamlParser;
import com.appvenir.init.CliContext;
import com.appvenir.init.CliLauncher;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class App 
{
    public static void main( String[] args )
    {
        CliLauncher.launch();
        String projectRootDir = "/Users/Fred/cli/jpi";

        ActiveProjectDetailsFactory activeProjectDetailsFactory = ActiveProjectDetailsFactory.newInstance(projectRootDir, CliContext.getCliConfig());
        ActiveProjectDetails activeProjectDetails = activeProjectDetailsFactory.createFromProject();
        Logger.info(activeProjectDetails.getAppRootPath());

        Schemas schemas = CliContext.getSchemas();
        
        String id = "domain";
        DirSchema dirSchema = schemas.getDirSchema("domain")
                                .orElseThrow(() -> new NoSuchElementException("Could not find a dirSchema with id: " + id));

        CreateFileStructureAction createFileStructureAction = new CreateFileStructureAction(dirSchema, activeProjectDetails);

        try {
            createFileStructureAction.execute();
        } catch (Exception e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        }
        
        // String appDir = App.getAppDirectoryPath();
        // System.out.println("App is running from: " + appDir);   
     }

    // public static String getAppDirectoryPath() {
    //     try {
    //         File jarFile = new File(App.class
    //             .getProtectionDomain()
    //             .getCodeSource()
    //             .getLocation()
    //             .toURI());

    //         // If this is a jar file, return its parent directory
    //         File dir = jarFile.isFile() ? jarFile.getParentFile() : jarFile;
    //         return dir.getAbsolutePath();

    //     } catch (URISyntaxException e) {
    //         throw new RuntimeException("Unable to determine application directory", e);
    //     }
    // }

}
