package com.appvenir.core.commands;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import com.appvenir.api.springInitializer.SpringInitApi;
import com.appvenir.core.action.DownloadSpringBootAction;
import com.appvenir.core.valueObjects.PackageName;
import com.appvenir.infrastructure.http.HttpClientBuilderFactory;
import com.appvenir.infrastructure.http.HttpService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
    name = "spring-boot"
)
public class SpringBootInitializerCommand implements Runnable{

    @Parameters(
        index = "0",
        description = "Package name"
    )
    private String packageName;

    @Parameters(
        index = "1",
        description = "Root folder name"
    )
    private String rootPath;

    @Override
    public void run() {

        Path targetPath = Paths.get("/Users/Fred/temp");

        HttpService httpService = new HttpService(HttpClientBuilderFactory.getBaseHttpClentBuilder());
        SpringInitApi springInitApi = new SpringInitApi("https://start.spring.io/starter.zip", httpService);

        Map<String, String> springProjectDetails = new HashMap<>();

        if(!".".equals(rootPath))
        {
            springProjectDetails.put("baseDir", rootPath);
        }

        PackageName currentPackageName = PackageName.create(packageName);

        springProjectDetails.put("type", "maven-project");
        springProjectDetails.put("language", "java");
        springProjectDetails.put("bootVersion", "3.4.4");
        springProjectDetails.put("groupId", getGroupIdFromPackage(currentPackageName));
        springProjectDetails.put("artifactId", getName(rootPath, currentPackageName));
        springProjectDetails.put("name", getName(rootPath, currentPackageName));
        springProjectDetails.put("description", "This is a demo");
        springProjectDetails.put("packageName", currentPackageName.value());
        springProjectDetails.put("packaging", "jar");
        springProjectDetails.put("javaVersion", "17");
        springProjectDetails.put("dependencies", "data-jpa, data-jdbc");

        System.out.println(springProjectDetails);
        

        DownloadSpringBootAction downloadSpringBootAction = new DownloadSpringBootAction(targetPath, springInitApi, springProjectDetails);

        try {
            downloadSpringBootAction.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getName(String rootPath, PackageName packageName)
    {
        if(!".".equals(rootPath))
        {
            return rootPath;
        }else {
            return packageName.getLastSegment();
        }
    }

    public String getGroupIdFromPackage(PackageName packageName) {
        String value = packageName.value();
        String[] splitValue = value.split("\\.");
    
        // If there is only one element in the package name, return it directly
        if (splitValue.length == 1) {
            return value;
        }
    
        // If there are multiple parts, return the string excluding the last part
        return value.substring(0, value.lastIndexOf("."));
    }
    
}
