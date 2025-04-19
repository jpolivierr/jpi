package com.appvenir.infrastructure.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.http.HttpResponse.BodyHandlers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.appvenir.TestServer;

public class HttpServiceTest {

    static TestServer testServer;

    @BeforeAll
    static void setUp() throws IOException
    {
        testServer = new TestServer();
        testServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException
    {
        testServer.stop();
    }

    @Test
    void should_return_status_200()
    {
        HttpService httpService = new HttpService(HttpClientBuilderFactory.getBaseHttpClentBuilder());

        String url = testServer.ok();
        ResponseState<String> response = httpService.get(url, BodyHandlers.ofString());

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatus());
        assertNotNull(response.getBody());
    }

    @Test
    void should_return_status_404()
    {
        HttpService httpService = new HttpService(HttpClientBuilderFactory.getBaseHttpClentBuilder());

        String url = testServer.notFound();
        ResponseState<String> response = httpService.get(url, BodyHandlers.ofString());

        assertNotNull(response);
        assertTrue(!response.isSuccess());
        assertEquals(404, response.getStatus());
        assertNotNull(response.getBody());
    }

    @Test
    void should_return_status_400()
    {
        HttpService httpService = new HttpService(HttpClientBuilderFactory.getBaseHttpClentBuilder());

        String url = testServer.badRequest();
        ResponseState<String> response = httpService.get(url, BodyHandlers.ofString());

        assertNotNull(response);
        assertTrue(!response.isSuccess());
        assertEquals(400, response.getStatus());
        assertNotNull(response.getBody());
    }

    @Test
    void should_run_command_on_success()
    {
        HttpService httpService = new HttpService(HttpClientBuilderFactory.getBaseHttpClentBuilder());

        String url = testServer.ok();
        ResponseState<String> response = httpService.get(url, BodyHandlers.ofString());

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatus());
        assertNotNull(response.getBody());

        String successData = response.onSuccess((body) -> {
            return "Test data!";
        });

        String errorData = response.onError((body) -> {
            return "This is an error";
        });

        String exceptionData = response.onServiceFailure((errorResponse) -> {
            return errorResponse.getMessage();
        });

        assertEquals("Test data!", successData);
        assertNull(errorData);
        assertNull(exceptionData);
    }

    @Test
    void should_run_command_on_error()
    {
        HttpService httpService = new HttpService(HttpClientBuilderFactory.getBaseHttpClentBuilder());

        String url = testServer.badRequest();
        ResponseState<String> response = httpService.get(url, BodyHandlers.ofString());

        assertNotNull(response);
        assertTrue(!response.isSuccess());
        assertEquals(400, response.getStatus());
        assertNotNull(response.getBody());

        String successData = response.onSuccess((body) -> {
            return "Test data!";
        });

        String errorData = response.onError((body) -> {
            return "This is an error";
        });

        String exceptionData = response.onServiceFailure((errorResponse) -> {
            return errorResponse.getMessage();
        });

        assertEquals("This is an error", errorData);
        assertNull(successData);
        assertNull(exceptionData);
    }

    @Test
    void should_run_command_on_service_exception()
    {
        HttpService httpService = new HttpService(HttpClientBuilderFactory.getBaseHttpClentBuilder());

        String url = testServer.broken();
        ResponseState<String> response = httpService.get(url, BodyHandlers.ofString());

        assertNotNull(response);
        assertTrue(!response.isSuccess());
        assertEquals(500, response.getStatus());
        assertNull(response.getBody());

        String successData = response.onSuccess((body) -> {
            return "Test data!";
        });

        String errorData = response.onError((body) -> {
            return "This is an error";
        });

        String exceptionData = response.onServiceFailure((errorResponse) -> {
            return errorResponse.getMessage();
        });

        assertNull(successData);
        assertNull(errorData);
        assertNotNull(exceptionData);
    }
    
    
}
