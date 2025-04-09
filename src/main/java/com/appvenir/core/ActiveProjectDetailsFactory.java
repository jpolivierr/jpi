package com.appvenir.core;

import java.util.List;
import java.util.Optional;

import com.appvenir.infrastructure.config.ConfigLoader;
import com.appvenir.infrastructure.system.io.IO;
import com.appvenir.infrastructure.system.parcers.buildTool.BuildToolFactory;
import com.appvenir.infrastructure.system.parcers.buildTool.BuildToolParser;

public class ActiveProjectDetailsFactory {
    private final String rootDir;
    private final ConfigLoader configLoader;

    public ActiveProjectDetailsFactory(String rootDir, ConfigLoader configLoader){
        this.rootDir = rootDir;
        this.configLoader = configLoader;
    }

    public static ActiveProjectDetailsFactory newInstance(String rootDir, ConfigLoader configLoader)
    {
        return new ActiveProjectDetailsFactory(rootDir, configLoader);
    }

    public ActiveProjectDetails createFromProject() 
    {
        String buildToolFilename = getBuildToolFileName();
        return new ActiveProjectDetails.Builder()
                    .setRootDir(rootDir)
                    .setSourceDir(configLoader.getProjectSourceDir())
                    .setBuildToolFileName(buildToolFilename)
                    .setRootPackageName(getPackageName(buildToolFilename))
                    .build();
    }



    private String getBuildToolFileName()
    {
        List<String> currentFiles = IO.getFileNamesFromDir(rootDir);
        List<String> buildTollFileNames = configLoader.getBuildToolFileNames();
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
        return buildToolParcer.getProjectPackage(rootDir + "/" + buildToolFilename);
    }
    
}
