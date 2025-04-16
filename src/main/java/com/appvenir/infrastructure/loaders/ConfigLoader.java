package com.appvenir.infrastructure.loaders;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import com.appvenir.infrastructure.config.CliConfig;
import com.appvenir.system.logger.Logger;

public class ConfigLoader {
    private static ConfigLoader instance;
    private final Properties properties;
    private final String configFile;
    private boolean propertyLoaded = false;
    private final CliConfig cliConfig;

    private ConfigLoader(String configFile)
    {
        properties = new Properties();
        this.configFile = configFile;
        loadProperties();
        this.cliConfig = buildCliConfig();
    }

    private void loadProperties()
    {
        if(!propertyLoaded)
        {
            try (FileInputStream inputStream = new FileInputStream(configFile)) 
            {
                Logger.info("Loading configuration.");
                properties.load(inputStream);
                propertyLoaded = true;
            } catch (Exception e) 
            {
                Logger.error("Could not load config file.");
            }
        }else {
            Logger.warn("Properties already loaded");
        }
    }

    private List<String> getBuildToolFileNames()
    {
        String fileNames = properties.getProperty("project.buildTool.fileNames");
        if(fileNames == null)
        {
            return Collections.emptyList();
        }
        return Arrays.asList(fileNames.split(","));
    }

    public CliConfig buildCliConfig()
    {
        return new CliConfig.Builder()
            .setWorkSpaceTemplatePath(properties.getProperty("app.schemas"))
            .setJavaProjectPath(properties.getProperty("project.java.dir"))
            .setBuildToolFileNames(getBuildToolFileNames())
            .build();
    }

    public CliConfig getCliConfig()
    {
        return cliConfig;
    }

    public static ConfigLoader getInstance(String configFile) 
    {
        if (instance == null) 
        {
            instance = new ConfigLoader(configFile);
        }
        return instance;
    }
}
