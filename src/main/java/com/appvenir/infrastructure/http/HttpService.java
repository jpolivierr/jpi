package com.appvenir.infrastructure.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandler;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

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

    public <T> ResponseState<T> get(String url, Map<String,String> parameters, BodyHandler<T> responseBodyHandler)
    {
        try {

            if (parameters != null && !parameters.isEmpty()) {
                url += toUrlParams(parameters);
            }

            System.out.println(url);
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

    public <T> ResponseState<T> post(String url, Object requestBody, BodyHandler<T> responseBodyHandler)
    {
        try {
            HttpRequest request = HttpRequest
                                .newBuilder(URI.create(url))
                                .header("Content-Type", "application/json")
                                .header("Accept", "application/zip") // this is crucial
                                .POST(BodyPublishers.ofString(convertToJson(requestBody)))
                                .build();

            HttpResponse<T> response = httpClient.send(request, responseBodyHandler);
            return ResponseState.newInstance(response.statusCode(), response.body());
        } 
        catch (IOException | InterruptedException e) {
            return ResponseState.newInstance(500, new ErrorResponse(e));
        }
    }

    public static String toUrlParams(Map<String, String> parameters) {
    if (parameters == null || parameters.isEmpty()) {
        return "";
    }
    return parameters.entrySet().stream()
            .map(entry -> encode(entry.getKey()) + "=" + encode(entry.getValue()))
            .collect(Collectors.joining("&", "?", ""));
}

    private static String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> String convertToJson(Object requestBody) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(requestBody);
    }
    
}
