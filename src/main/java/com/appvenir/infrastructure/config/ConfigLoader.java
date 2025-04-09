package com.appvenir.infrastructure.config;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import com.appvenir.infrastructure.system.logger.Logger;

public class ConfigLoader {
    private static ConfigLoader instance;
    private final Properties properties;

    private ConfigLoader(String configFile)
    {
        properties = new Properties();
        loadProperties(configFile);
    }

    private void loadProperties(String configFile)
    {
        try (FileInputStream inputStream = new FileInputStream(configFile)) {
            Logger.info("Loading configuration.");
            properties.load(inputStream);
        } catch (Exception e) {
            Logger.error("Could not load config file.");
        }
    }

    public String getSchemasPath()
    {
        return properties.getProperty("app.schemas");
    }

    public String getProjectSourceDir()
    {
        return properties.getProperty("project.source.dir");
    }

    public List<String> getBuildToolFileNames()
    {
        String fileNames = properties.getProperty("project.buildTool.fileNames");
        if(fileNames == null)
        {
            return Collections.emptyList();
        }
        return Arrays.asList(fileNames.split(","));
    }

    public static ConfigLoader getInstance(String configFile) {
        if (instance == null) {
            instance = new ConfigLoader(configFile);
        }
        return instance;
    }
}
