package com.appvenir.core.exceptions;

import java.nio.file.Path;

public class NotADirectoryException extends RuntimeException{
    public NotADirectoryException(Path path)
    {
        super("Path is not a directory: " + path.toString());
    }
}
