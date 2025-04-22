package com.appvenir.infrastructure.http;

public class ErrorResponse {
    private final String message;
    private final String exceptionName;
    private final Exception exception;

    public ErrorResponse(Exception ex)
    {
        this.exceptionName = ex.getClass().getName();
        this.message = ex.getMessage();
        this.exception = ex;
    }

    public String getMessage() {
        return message;
    }

    public String getExceptionName() 
    {
        return exceptionName;
    }

    public Exception getException()
    {
        return exception;
    }

}
