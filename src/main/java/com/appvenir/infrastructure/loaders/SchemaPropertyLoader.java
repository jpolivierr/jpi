package com.appvenir.infrastructure.loaders;

import java.io.IOException;
import com.appvenir.infrastructure.system.logger.Logger;
import com.appvenir.infrastructure.system.parcers.file.FileParser;

public class SchemaPropertyLoader {
    private static SchemaPropertyLoader instance;
    private final FileParser fileParser;

    private SchemaPropertyLoader(FileParser fileParser) 
    {
        this.fileParser = fileParser;
    }

    public <T> T getSchemas(String filePath, Class<T> clazz) 
    {
        try 
        {
            return fileParser.getObjectValue(filePath, clazz);
        } catch (IOException e) 
        {
            Logger.error("Failed to load schemas from file: " + filePath);
            System.exit(1);
            return null;
        }
    }

    public static SchemaPropertyLoader getInstance(FileParser fileParser) 
    {
        if (instance == null) 
        {
            instance = new SchemaPropertyLoader(fileParser);
        }
        return instance;
    }

}
