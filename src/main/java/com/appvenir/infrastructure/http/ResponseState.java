package com.appvenir.infrastructure.http;

import java.util.function.Consumer;

public class ResponseState<T> {
    private final int status;
    private final T body;
    private ErrorResponse errors;

    private ResponseState(int status, T body)
    {
        this.status = status;
        this.body = body;
    }

    public static <T> ResponseState<T> newInstance(int status, T body)
    {
        return new ResponseState<>(status, body);
    }

    public static <T> ResponseState<T> newInstance(int status, ErrorResponse errors)
    {
        ResponseState<T> responseState = new ResponseState<>(status, null);
        responseState.errors = errors;
        return responseState;
    }

    public boolean isSuccess()
    {
        return status >= 200 && status <= 299;
    }

    public void onSuccess(Consumer<T> consumer){
        if(isSuccess())
        {
            consumer.accept(body);
        }
    }

    public void onFailure(Consumer<ErrorResponse> consumer){
        if(isSuccess())
        {
            consumer.accept(errors);
        }
    }

    public int getStatus() {
        return status;
    }

    public T getBody() {
        return body;
    }

    public ErrorResponse getErrors() {
        return errors;
    }
}
