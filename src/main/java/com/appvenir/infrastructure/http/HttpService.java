package com.appvenir.infrastructure.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpService {
    private final HttpClient httpClient;

    public HttpService(HttpClient.Builder httpBuilder)
    {
        this.httpClient = httpBuilder.build();
    }

    public <T> ResponseState<T> get(String url, BodyHandler<T> responseBodyHandler)
    {
        try {
            HttpRequest request = HttpRequest
                                .newBuilder(URI.create(url))
                                .GET()
                                .build();

            HttpResponse<T> response = httpClient.send(request, responseBodyHandler);
            return ResponseState.newInstance(response.statusCode(), response.body());
        } catch (IOException | InterruptedException e) {
            return ResponseState.newInstance(500, new ErrorResponse(e));
        }
    }

    public <T> ResponseState<T> get(String url, BodyHandler<T> responseBodyHandler, Runnable runnable)
    {
        try {
            HttpRequest request = HttpRequest
                                .newBuilder(URI.create(url))
                                .GET()
                                .build();
                                
            runnable.run();
            HttpResponse<T> response = httpClient.send(request, responseBodyHandler);
            return ResponseState.newInstance(response.statusCode(), response.body());
        } catch (IOException | InterruptedException e) {
            return ResponseState.newInstance(500, new ErrorResponse(e));
        }
    }

    public <T> ResponseState<T> post(String url, Object requestBody, BodyHandler<T> responseBodyHandler)
    {
        try {
            HttpRequest request = HttpRequest
                                .newBuilder(URI.create(url))
                                .POST(BodyPublishers.ofString(convertToJson(requestBody)))
                                .build();

            HttpResponse<T> response = httpClient.send(request, responseBodyHandler);
            return ResponseState.newInstance(response.statusCode(), response.body());
        } 
        catch (IOException | InterruptedException e) {
            return ResponseState.newInstance(500, new ErrorResponse(e));
        }
    }

    public <T> String convertToJson(Object requestBody) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(requestBody);
    }
    
}
