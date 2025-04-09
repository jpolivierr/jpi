package com.appvenir.infrastructure.config;

import java.io.IOException;

import com.appvenir.core.model.Schemas;
import com.appvenir.infrastructure.system.logger.Logger;
import com.appvenir.infrastructure.system.parcers.file.FileParser;

public class SchemaPropertyLoader {
    private static SchemaPropertyLoader instance;
    private final ConfigLoader configLoader;
    private final FileParser fileParser;
    private final Schemas schemas;

    private SchemaPropertyLoader(ConfigLoader configLoader, FileParser fileParser) 
    {
        this.configLoader = configLoader;
        this.fileParser = fileParser;
        this.schemas = loadSchemas(configLoader.getSchemasPath());
    }

    private Schemas loadSchemas(String filePath) 
    {
        try 
        {
            return fileParser.getObjectValue(filePath, Schemas.class);
        } catch (IOException e) 
        {
            Logger.error("Failed to load schemas from file: " + filePath);
            System.exit(1);
            return null;
        }
    }

    public Schemas getSchemas() 
    {
        return schemas;
    }

    public static SchemaPropertyLoader getInstance(ConfigLoader configLoader, FileParser fileParser) 
    {
        if (instance == null) 
        {
            instance = new SchemaPropertyLoader(configLoader, fileParser);
        }
        return instance;
    }

    public ConfigLoader getConfigLoader() 
    {
        return configLoader;
    }

}
