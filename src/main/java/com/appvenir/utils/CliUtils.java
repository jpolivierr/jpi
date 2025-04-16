package com.appvenir.utils;

import com.appvenir.system.logger.Logger;

public class CliUtils {

    public static boolean isValidJavaClassName(String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Class name cannot be null or empty.");
            }
    
            // Must be a valid Java identifier
            if (!Character.isJavaIdentifierStart(name.charAt(0))) {
                throw new IllegalArgumentException("Class name must start with a valid identifier character.");
            }
    
            for (int i = 1; i < name.length(); i++) {
                if (!Character.isJavaIdentifierPart(name.charAt(i))) {
                    throw new IllegalArgumentException("Class name contains invalid characters.");
                }
            }
    
            // Cannot be a Java keyword
            if (isJavaKeyword(name)) {
                throw new IllegalArgumentException("Class name cannot be a Java keyword.");
            }

            return true;
        } catch (IllegalArgumentException e) {
            Logger.error(e.getMessage());
            return false;
        }

    }
    
    private static boolean isJavaKeyword(String word) {
        String[] keywords = {
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
            "class", "const", "continue", "default", "do", "double", "else", "enum",
            "exports", "extends", "final", "finally", "float", "for", "goto", "if",
            "implements", "import", "instanceof", "int", "interface", "long", "module",
            "native", "new", "opens", "package", "private", "protected", "public",
            "requires", "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "try", "void",
            "volatile", "while"
        };

        for (String keyword : keywords) {
            if (keyword.equals(word)) {
                return true;
            }
        }

        return false;
    }
}
