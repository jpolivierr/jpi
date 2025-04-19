package com.appvenir;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class TestServer {

    public final int PORT = 2020;
    public final String URL = "http://localhost:2020";
    private HttpServer httpServer;

    public TestServer() throws IOException {}

    public void start() throws IOException {
        this.httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);

        // Mock 200 OK
        this.httpServer.createContext("/200", exchange -> {
            String response = "Success!";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });

        // Mock 400 Bad Request
        this.httpServer.createContext("/400", exchange -> {
            String response = "Bad Request!";
            exchange.sendResponseHeaders(400, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });

        // Mock 500 Internal Server Error
        this.httpServer.createContext("/500", exchange -> {
            String response = "Internal Server Error!";
            exchange.sendResponseHeaders(500, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });

        // Mock 500 Internal Server Error
        this.httpServer.createContext("/404", exchange -> {
            String response = "Internal Server Error!";
            exchange.sendResponseHeaders(404, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });

        this.httpServer.createContext("/throw", exchange -> {
            throw new IOException("Simulated server crash");
        });

        this.httpServer.createContext("/broken", exchange -> {
            exchange.getResponseBody().close(); // close without sending headers
        });

        httpServer.setExecutor(null);
        httpServer.start();
        System.out.println("TestServer running on " + URL);
    }

    public void stop() {
        if (httpServer != null) {
            httpServer.stop(0);
            System.out.println("TestServer stopped.");
        }
    }

    public String ok()
    {
        return URL + "/200";
    }

    public String notFound()
    {
        return URL + "/404";
    }

    public String badRequest()
    {
        return URL + "/400";
    }

    public String internalError()
    {
        return URL + "/500";
    }

    public String broken()
    {
        return URL + "/broken";
    }
}
