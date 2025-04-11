package com.appvenir.core.action;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

import com.appvenir.core.ActiveProjectDetails;
import com.appvenir.core.model.DirSchema;
import com.appvenir.infrastructure.system.io.IO;
import com.appvenir.infrastructure.system.logger.Logger;

public class CreateFileStructureAction implements FileAction {
    private final int MAX_FILE_CREATION = 5;
    private final DirSchema dirSchema;
    private final int pathCount;
    private final List<String> paths;
    private final String appRootPath;

    public CreateFileStructureAction(DirSchema dirSchema, ActiveProjectDetails activeProjectDetails)
    {
        this.dirSchema = dirSchema;
        this.pathCount = dirSchema.getPaths().size();
        this.paths = dirSchema.getPaths();
        this.appRootPath = activeProjectDetails.getAppRootPath();
        if(pathCount > MAX_FILE_CREATION)
        {
            throw new IllegalStateException("Maximum path creation reached.");
        }
    }

    private void createPaths() throws IOException
    {
        for(String path : paths)
        {
            try {
                Logger.info("Create path: " + appRootPath + path);
                IO.createPathIfNotExist(appRootPath + path);
            } 
            catch (FileAlreadyExistsException e) {
                Logger.warn("taskId: " + dirSchema.getId() + "File path already exists: " + path);
            }
        }
    }

    private void deletePaths() throws IOException
    {
        for(String path : paths)
        {
            Logger.info("Delete path: " + appRootPath + path);
            IO.deletePath(appRootPath + path);
        }
    }

    @Override
    public void execute() throws IOException {
        Logger.info("START: [PATH CREATION] -> " + dirSchema.getId());
        createPaths();
        Logger.info("END: [PATH CREATION] -> " + dirSchema.getId());
    }

    @Override
    public void undo() throws IOException{
        deletePaths();
    }
    
}
