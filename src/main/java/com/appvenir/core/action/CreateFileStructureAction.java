package com.appvenir.core.action;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

import com.appvenir.core.model.PathLayout;
import com.appvenir.system.io.IO;
import com.appvenir.system.logger.Logger;

public class CreateFileStructureAction implements FileAction {
    private final int MAX_FILE_CREATION = 5;
    private final PathLayout pathLayout;
    private final int pathCount;
    private final List<String> paths;
    private final String targetPath;

    public CreateFileStructureAction(PathLayout pathLayout, String targetPath)
    {
        this.pathLayout = pathLayout;
        this.pathCount = pathLayout.getPaths().size();
        this.paths = pathLayout.getPaths();
        this.targetPath = targetPath;
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
                Logger.info("Create path: " + targetPath + path);
                IO.createPathIfNotExist(targetPath + path);
            } 
            catch (FileAlreadyExistsException e) {
                Logger.warn("taskId: " + pathLayout.getId() + "File path already exists: " + path);
            }
        }
    }

    private void deletePaths() throws IOException
    {
        for(String path : paths)
        {
            Logger.info("Delete path: " + targetPath + path);
            IO.deletePath(targetPath + path);
        }
    }

    @Override
    public void execute() throws IOException {
        Logger.info("START: [PATH CREATION] -> " + pathLayout.getId());
        createPaths();
        Logger.info("END: [PATH CREATION] -> " + pathLayout.getId());
    }

    @Override
    public void undo() throws IOException{
        deletePaths();
    }
    
}
