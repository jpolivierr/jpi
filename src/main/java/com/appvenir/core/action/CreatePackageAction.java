package com.appvenir.core.action;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

import com.appvenir.core.PackageName;
import com.appvenir.infrastructure.system.io.IO;
import com.appvenir.infrastructure.system.logger.Logger;
import com.appvenir.utils.StringUtils;

public class CreatePackageAction implements FileAction {
    private static final int MAX_FILE_COUNT = 10;
    private final PackageName packageName;
    private final List<String> sourcePaths;

    public CreatePackageAction(PackageName packageName, List<String> fileNames, String targetPath)
    {
        this.packageName = packageName;
        if(fileNames.size() > MAX_FILE_COUNT)
        {
            throw new IllegalArgumentException("Maximumn file count reached");
        }
        this.sourcePaths = new ArrayList<>();
        for(String fileName : fileNames)
        {
            this.sourcePaths.add(targetPath + StringUtils.convertPackagetoPath(packageName) + "/" + fileName);
        }
    }

    private void createPackage() throws IOException
    {
        for(String sourcePath : sourcePaths)
        {
            try {
                Logger.info("Create path: " + sourcePath);
                IO.createFileIfNotExist(sourcePath);
            } 
            catch (FileAlreadyExistsException e) {
                Logger.warn("File path already exists: " + sourcePath);
            }
        }
    }

    private void deletePaths() throws IOException
    {
        for(String sourcePath : sourcePaths)
        {
                Logger.info("Delete path: " + sourcePath);
                IO.deletePath(sourcePath);
        }
    }

    @Override
    public void execute() throws IOException {
        Logger.info("START: [PACKAGE CREATION] -> " + packageName);
        createPackage();
        Logger.info("END: [PATH CREATION] -> " + packageName);
    }

    @Override
    public void undo() throws IOException{
        deletePaths();
    }
    
}

