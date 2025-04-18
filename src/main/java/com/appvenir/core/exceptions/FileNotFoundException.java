package com.appvenir.core.exceptions;

import java.nio.file.Path;

public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException(Path path)
    {
        super("Path not found: " + path.toAbsolutePath().toString());
    }
        
}
