package com.appvenir.exceptions;

public class InvalidPackageNameException extends RuntimeException{
    public InvalidPackageNameException(String value)
    {
        super("Invalid package name: " + value);
    }
}
