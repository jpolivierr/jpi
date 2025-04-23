package com.appvenir.api.springInitializer;

import java.io.InputStream;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.appvenir.core.exceptions.FileNotFoundException;
import com.appvenir.core.exceptions.NotADirectoryException;
import com.appvenir.core.exceptions.NotAnEmptyDirectoryException;
import com.appvenir.core.valueObjects.FileAttribute;
import com.appvenir.infrastructure.http.HttpService;
import com.appvenir.infrastructure.http.ResponseState;
import com.appvenir.system.io.IO;

public class SpringInitApi {
    private final String domain;
    private final HttpService httpService;

    public SpringInitApi(String domain, HttpService httpService)
    {
        this.domain = domain;
        this.httpService = httpService;
    }

    public FileAttribute downloadSpringBootApp(Path path, Map<String, String> springProjectDetails) throws Exception
    {
        IO.pathDownloadValidation(path);

        ResponseState<InputStream> response = httpService.get(domain, springProjectDetails, BodyHandlers.ofInputStream());

        if(response.getErrors() != null)
        {
            throw response.getErrors().getException();
        }

        if(response.isSuccess()){
            System.out.println("Start downloading project");
            IO.unzip(response.getBody(), path);
            FileAttribute fileAttribute;
            try (Stream<Path> entries = Files.list(path)) {
                List<Path> topLevelEntries = entries.collect(Collectors.toList());

                if (topLevelEntries.size() == 1) {
                    Path single = topLevelEntries.get(0);
                    fileAttribute = new FileAttribute(single);
                } else {
                    fileAttribute = new FileAttribute(path);
                }
            }
            System.out.println("Spring Boot project downloaded and extracted!");
            return fileAttribute;
        } else {
            return null;
        }
    }
    
}

