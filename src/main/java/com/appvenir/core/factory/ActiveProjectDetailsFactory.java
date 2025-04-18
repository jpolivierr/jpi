package com.appvenir.core.factory;

import java.util.List;
import java.util.Optional;

import com.appvenir.core.model.ActiveProjectDetails;
import com.appvenir.core.valueObjects.PackageName;
import com.appvenir.infrastructure.config.CliConfig;
import com.appvenir.system.io.IO;
import com.appvenir.system.parcers.buildTool.BuildToolFactory;
import com.appvenir.system.parcers.buildTool.BuildToolParser;

public class ActiveProjectDetailsFactory {
    private final String rootPath;
    private final CliConfig cliConfig;

    private ActiveProjectDetailsFactory(String rootPath, CliConfig cliConfig){
        this.rootPath = rootPath;
        this.cliConfig = cliConfig;
    }

    public static ActiveProjectDetailsFactory newInstance(String rootPath, CliConfig cliConfig)
    {
        return new ActiveProjectDetailsFactory(rootPath, cliConfig);
    }

    public ActiveProjectDetails createFromProject() 
    {
        String buildToolFilename = getBuildToolFileName();
        return new ActiveProjectDetails.Builder()
                    .setRootPath(rootPath)
                    .setSourcePath(cliConfig.getJavaProjectPath())
                    .setBuildToolFileName(buildToolFilename)
                    .setRootPackageName(PackageName.create(getPackageName(buildToolFilename)))
                    .build();
    }



    private String getBuildToolFileName()
    {
        List<String> currentFiles = IO.getFileNamesFromDir(rootPath);
        List<String> buildTollFileNames = cliConfig.getBuildToolFileNames();
        String currentProjectBuildToolFile = null;

        for(String fileName: buildTollFileNames)
        {
            if(currentFiles.contains(fileName))
            {
                currentProjectBuildToolFile = fileName;
                break;
            }
        }

        if(currentProjectBuildToolFile == null)
        {
            throw new IllegalStateException("Could not find a valid java build tool file");
        }

        return currentProjectBuildToolFile;
    }

    private String getPackageName(String buildToolFilename)
    {
        Optional<BuildToolParser> optionalBuildToolParcer = BuildToolFactory.getBuildToolParser(buildToolFilename);
        BuildToolParser buildToolParcer = optionalBuildToolParcer.orElseThrow(() -> new IllegalStateException("Could not find a valid build tool parser."));
        return buildToolParcer.getProjectPackage(rootPath + "/" + buildToolFilename);
    }
    
}
