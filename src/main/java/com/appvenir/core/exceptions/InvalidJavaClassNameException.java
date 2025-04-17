package com.appvenir.core.exceptions;

public class InvalidJavaClassNameException extends RuntimeException {
    public InvalidJavaClassNameException(String name)
    {
        super(
            new StringBuilder()
                .append("Invalid java class name: " + name)
                .append("\n - Cannot be null or empty.")
                .append("\n - Must start with a valid identifier character.")
                .append("\n - Cannot contain invalid characters.")
                .append("\n - Cannot be a Java keyword.")
                .toString()
        );
    }

}


