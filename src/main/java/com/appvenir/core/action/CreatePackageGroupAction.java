package com.appvenir.core.action;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.appvenir.core.valueObjects.JavaClassName;
import com.appvenir.core.valueObjects.PackageName;
import com.appvenir.system.io.IO;
import com.appvenir.system.logger.Logger;
import com.appvenir.utils.StringUtils;

public class CreatePackageGroupAction implements FileAction {
    private final int MAX_FILE_CREATION = 10;
    private final Map<String, String>  pathMap;
    private final String targetPath;
    private final List<String> paths;
    private boolean hasFiles = false;

    public CreatePackageGroupAction(Map<String, String> pathMap, String[] fileNames, String targetPath, boolean inverse)
    {
        if(pathMap.size() > MAX_FILE_CREATION)
        {
            throw new IllegalStateException("Maximum path creation reached.");
        }
        this.pathMap = pathMap;
        this.targetPath = targetPath;
        this.hasFiles = fileNames.length > 0;
        this.paths = !inverse ? generatePath(fileNames) : generateInversePath(fileNames);
    }

    private void createPackages() throws IOException
    {
        for(String path : paths)
        {
            try {
                if(hasFiles){
                    Logger.info("Create file: " + path);
                    IO.createFileIfNotExist(path);
                }else {
                    Logger.info("Create path: " + path);
                    IO.createPathIfNotExist(path);
                }
            } 
            catch (FileAlreadyExistsException e) {
                Logger.warn("File path already exists: " + path);
            }
        }
    }

    private void deletePaths() throws IOException
    {
        for(String path : paths)
        {
            Logger.info("Delete path: " + path);
            IO.deletePath(path);
        }
    }

    public List<String> generatePath(String[] fileNames)
    {

        List<String> filePaths = new ArrayList<>();
        for (Map.Entry<String, String> entry : pathMap.entrySet()) 
        {
            String packageId = entry.getKey();
            String currentPackage = entry.getValue();

            PackageName packageName = PackageName.create(currentPackage);
            String parentPath = StringUtils.convertPackagetoPath(packageName);

            if(hasFiles)
            {
                for(String fileName : fileNames)
                {
                    JavaClassName javaClassName = JavaClassName.create(fileName).suffixed(packageId);
                    filePaths.add(targetPath + parentPath + javaClassName.getFilePath());
                }
            }else {
                filePaths.add(targetPath + parentPath);
            }
        }
        return filePaths;
    }

    public List<String> generateInversePath(String[] fileNames)
    {
        List<String> filePaths = new ArrayList<>();

        for(String fileName : fileNames) {

            for (Map.Entry<String, String> entry : pathMap.entrySet()) {

                String packageId = entry.getKey();
                String currentPackage = entry.getValue();

                PackageName packageName = PackageName.create(currentPackage);
                String lastPackageSegment = StringUtils.getLastPackageSegment(packageName);
                JavaClassName javaClassName = JavaClassName.create(fileName);
                PackageName featurePackage = StringUtils.replaceLastSegment(packageName, javaClassName.value()).add(lastPackageSegment);
                String parentPath = StringUtils.convertPackagetoPath(featurePackage);
                JavaClassName javaFileName = javaClassName.suffixed(packageId);
                filePaths.add(targetPath + parentPath + javaFileName.getFilePath());
            }

        }
        return filePaths;
    }

    @Override
    public void execute() throws IOException {
        createPackages();
    }

    @Override
    public void undo() throws IOException{
        deletePaths();
    }
    
}

