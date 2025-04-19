package com.appvenir.infrastructure.http;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.time.Duration;

public class HttpClientBuilderFactory {

    public static HttpClient.Builder getBaseHttpClentBuilder()
    {
        return HttpClient.newBuilder()
                .followRedirects(Redirect.NORMAL)
                .connectTimeout(Duration.ofMinutes(1));
    }
    
}
