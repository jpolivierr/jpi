package com.appvenir.core.exceptions;

import java.nio.file.Path;

public class NotAnEmptyDirectoryException extends RuntimeException {
    public NotAnEmptyDirectoryException(Path path)
    {
        super("Path not empty");
    }
}
