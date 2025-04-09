package com.appvenir.infrastructure.system.parcers.file;

import java.io.IOException;

public interface FileParser {
    public <T> T getObjectValue(String filePath, Class<T> object) throws IOException;
}
