package com.appvenir.infrastructure.init;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CliContext {
    private static CliContext instance;
    private final Map<String, Object> beans;

    public CliContext() {
        this.beans = new ConcurrentHashMap<>();
    }

    public <T> void registerBean(Class<T> clazz, T object) {
        beans.put(clazz.getName(), object);
    }

    public static <T> T getBean(Class<T> clazz) {
        if(instance == null)
        {
            throw new IllegalStateException("Clicontext has not been initialized");
        }
        return clazz.cast(instance.beans.get(clazz.getName()));
    }

    public static CliContext getInstance()
    {
        if(instance == null)
        {
            CliContext cliContext = new CliContext();
            instance = cliContext;
            return instance;
        } 
        else 
        {
            return instance;
        }
    }
    
}
