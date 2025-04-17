package com.appvenir.infrastructure.http;

public class ErrorResponse {
    private final String message;
    private final String exception;

    public ErrorResponse(Exception ex)
    {
        this.exception = ex.getClass().getName();
        this.message = ex.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public String getException() {
        return exception;
    }

}
