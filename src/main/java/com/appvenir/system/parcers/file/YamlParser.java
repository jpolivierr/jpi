package com.appvenir.system.parcers.file;
import java.io.File;
import java.io.IOException;

import com.appvenir.infrastructure.providers.MapperProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

public class YamlParser implements FileParser{
    private final ObjectMapper objectMapper;

    public YamlParser()
    {
        this.objectMapper = MapperProvider.getInstance().getYamlMapper();
    }

    @Override
    public <T> T getObjectValue(String filePath, Class<T> object) throws IOException {
        return objectMapper.readValue(new File(filePath), object);
    }

    public static YamlParser newInstant()
    {
        return new YamlParser();
    }
}
