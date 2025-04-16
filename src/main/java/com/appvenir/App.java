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
import com.appvenir.core.commands.PackageCommand;
import com.appvenir.core.commands.PackageGroupCommand;
import com.appvenir.core.commands.PathLayoutCommand;
import com.appvenir.core.javaFile.AccessModifier;
import com.appvenir.core.javaFile.ClassDetails;
import com.appvenir.core.javaFile.JavaFileDetails;
import com.appvenir.core.javaFile.JavaFileGenerator;
import com.appvenir.core.javaFile.PackageName;
import com.appvenir.core.javaFile.TypeVariable;
import com.appvenir.core.model.PackageGroup;
import com.appvenir.infrastructure.loaders.ConfigLoader;
import com.appvenir.infrastructure.providers.MapperProvider;
import com.appvenir.init.CliContext;
import com.appvenir.init.CliLauncher;
import com.appvenir.system.logger.Logger;
import com.appvenir.system.parcers.buildTool.BuildToolFactory;
import com.appvenir.system.parcers.buildTool.BuildToolParser;
import com.appvenir.system.parcers.buildTool.MavenBuildToolParser;
import com.appvenir.system.parcers.file.YamlParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
    name = "JPI",
    version = "1.0.0",
    description = """
                    JPI (Java Project Initializer) is a powerful CLI tool designed to streamline the creation and management of Java-based applications. 
                    It simplifies project setup, dependency management, and build processes, helping developers efficiently handle their Java projects from start to finish.
                 """,
    subcommands = { 
        PathLayoutCommand.class,
        PackageCommand.class,
        PackageGroupCommand.class
    }
)
public class App implements Runnable
{
    public static void main( String[] args )
    {
        CliLauncher.launch();

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("This is the main CLI application");
    }

        // ActiveProjectDetailsFactory activeProjectDetailsFactory = ActiveProjectDetailsFactory.newInstance(projectRootDir, CliContext.getCliConfig());
        // ActiveProjectDetails activeProjectDetails = activeProjectDetailsFactory.createFromProject();
        // Logger.info(activeProjectDetails.getAppRootPath());

        // Schemas schemas = CliContext.getSchemas();
        
        // String id = "domain";
        // DirSchema dirSchema = schemas.getDirSchema("domain")
        //                         .orElseThrow(() -> new NoSuchElementException("Could not find a dirSchema with id: " + id));

        // CreateFileStructureAction createFileStructureAction = new CreateFileStructureAction(dirSchema, activeProjectDetails);

        // try {
        //     createFileStructureAction.execute();
        // } catch (Exception e) {
        //     Logger.error(e.getMessage());
        //     e.printStackTrace();
        // }
        
        // String appDir = App.getAppDirectoryPath();
        // System.out.println("App is running from: " + appDir);   
     

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
