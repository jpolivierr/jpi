package com.appvenir.infrastructure.http;

import java.util.function.Consumer;
import java.util.function.Function;

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

    public void onSuccess(Consumer<T> consumer)
    {
        if(isSuccess())
        {
            consumer.accept(body);
        }
    }

    public <R> R onSuccess(Function<T,R> function)
    {
        if(isSuccess())
        {
            return function.apply(body);
        }else {
            return null;
        }
    }

    public void onError(Consumer<T> consumer)
    {
        if(!isSuccess() && errors == null)
        {
            consumer.accept(body);
        }
    }

    public <R> R onError(Function<T,R> function)
    {
        System.out.println(errors);
        if(!isSuccess() && errors == null)
        {
            return function.apply(body);
        } else {
            return null;
        }
    }

    public void onServiceFailure(Consumer<ErrorResponse> consumer)
    {
        if(errors != null)
        {
            consumer.accept(errors);
        }
    }

    public <R> R onServiceFailure(Function<ErrorResponse, R> function)
    {
        if(errors != null)
        {
            return function.apply(errors);
        } else {
            return null;
        }
    }

    public int getStatus() 
    {
        return status;
    }

    public T getBody() 
    {
        return body;
    }

    public ErrorResponse getErrors() 
    {
        return errors;
    }
}
