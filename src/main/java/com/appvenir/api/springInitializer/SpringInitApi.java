package com.appvenir.api.springInitializer;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.appvenir.core.exceptions.FileNotFoundException;
import com.appvenir.infrastructure.http.HttpClientBuilderFactory;
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

    public void downloadSpringBootApp(String targetPath, SpringProjectDetails springProjectDetails) throws IOException
    {
        Path path = Paths.get(targetPath);
        if(!Files.exists(path))
        {
            throw new FileNotFoundException(path);
        }

        ResponseState<InputStream> response = httpService.post(domain, springProjectDetails, BodyHandlers.ofInputStream());

        response.onSuccess( (body) -> {
            System.out.println("Start downloading project");
            try {
                IO.unzip(body, path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Spring Boot project downloaded and extracted!");
        });

    }

    public static void main(String[] args) throws IOException {


        HttpService httpService = new HttpService(HttpClientBuilderFactory.getBaseHttpClentBuilder());
        SpringInitApi springInitApi = new SpringInitApi("https://start.spring.io/starter.zip", httpService);

        SpringProjectDetails project = SpringProjectDetails.builder()
            .type("maven-project")
            .language("java")
            .bootVersion("3.2.0")
            .groupId("com.example")
            .artifactId("demo")
            .name("demo")
            .packageName("com.example.demo")
            .packaging("jar")
            .javaVersion("17")
            .dependencies(List.of("web", "jpa"))
            .build();


        springInitApi.downloadSpringBootApp("/Users/Fred/cli/jpi", project);
    }
    
}

