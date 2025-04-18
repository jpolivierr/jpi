package com.appvenir.api.springInitializer;

import java.io.IOException;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.appvenir.core.exceptions.FileNotFoundException;
import com.appvenir.infrastructure.http.HttpService;

public class SpringInitApi {
    private final String domain;
    private final HttpService httpService;

    public SpringInitApi(String domain, HttpService httpService)
    {
        this.domain = domain;
        this.httpService = httpService;
    }

    public void downloadSpringBootApp(String targetPath, SpringProjectDetails springProjectDetails) throws IOException
    {
        Path path = Paths.get(targetPath);
        if(!Files.exists(path))
        {
            throw new FileNotFoundException(path);
        }

        Path tempZip = Files.createTempFile("spring-init", ".zip");

        httpService.post(domain, springProjectDetails, BodyHandlers.ofInputStream());

    }
    
}

