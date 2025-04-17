package com.appvenir.infrastructure.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;

public class HttpClientService {
    private final HttpClient httpClient;

    public HttpClientService(HttpClient.Builder httpBuilder)
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
    
}
