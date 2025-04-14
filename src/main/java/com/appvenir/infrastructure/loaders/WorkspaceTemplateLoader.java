package com.appvenir.infrastructure.loaders;

import java.io.IOException;
import com.appvenir.infrastructure.system.logger.Logger;
import com.appvenir.infrastructure.system.parcers.file.FileParser;

public class WorkspaceTemplateLoader {
    private static WorkspaceTemplateLoader instance;
    private final FileParser fileParser;

    private WorkspaceTemplateLoader(FileParser fileParser) 
    {
        this.fileParser = fileParser;
    }

    public <T> T getWorkspaceTemplate(String filePath, Class<T> clazz) 
    {
        try 
        {
            return fileParser.getObjectValue(filePath, clazz);
        } catch (IOException e) 
        {
            Logger.error("Failed to load workspace template from file: " + filePath);
            System.exit(1);
            return null;
        }
    }

    public static WorkspaceTemplateLoader getInstance(FileParser fileParser) 
    {
        if (instance == null) 
        {
            instance = new WorkspaceTemplateLoader(fileParser);
        }
        return instance;
    }

}
