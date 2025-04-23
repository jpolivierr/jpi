package com.appvenir.core.action;

import java.nio.file.Path;
import java.util.Map;
import com.appvenir.api.springInitializer.SpringInitApi;
import com.appvenir.core.valueObjects.FileAttribute;


public class DownloadSpringBootAction implements Action{
    private final Path targetPath;
    private final SpringInitApi springInitApi;
    private final Map<String, String> springProjectDetails;

    public DownloadSpringBootAction(Path targetPath, SpringInitApi springInitApi, Map<String, String> springProjectDetails)
    {
        this.targetPath = targetPath;
        this.springInitApi = springInitApi;
        this.springProjectDetails = springProjectDetails;
    }

    @Override
    public void execute() throws Exception {
        FileAttribute fileAttribute = springInitApi.downloadSpringBootApp(targetPath, springProjectDetails);
        System.out.println(fileAttribute);
    }

    
}
