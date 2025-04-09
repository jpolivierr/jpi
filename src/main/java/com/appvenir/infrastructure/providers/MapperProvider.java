package com.appvenir.infrastructure.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class MapperProvider {
    private static MapperProvider instance;
    private ObjectMapper yamlMapper;

    private MapperProvider(){
        this.yamlMapper = new ObjectMapper(new YAMLFactory());
    }

    public ObjectMapper getYamlMapper()
    {
        return this.yamlMapper;
    }

    public static MapperProvider getInstance() {
        if (instance == null) {
            instance = new MapperProvider();
        }
        return instance;
    }

}
