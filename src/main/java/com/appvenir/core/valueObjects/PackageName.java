package com.appvenir.core.valueObjects;

public class PackageName {
    private final String value;

    private PackageName(String value)
    {
        if (!isValidPackageName(value)) {
            throw new IllegalArgumentException("Invalid package name: " + value);
        }
        this.value = value.toLowerCase();
    }

    public static PackageName create(String name)
    {
        return new PackageName(name);
    }

    public String value() {
        return value;
    }

    public PackageName add(String name) {
        return new PackageName(this.value + "." + name);
    }

    private boolean isValidPackageName(String name) {
        if (name == null || name.isEmpty()) return false;

        String[] parts = name.split("\\.");
        for (String part : parts) {
            if (!part.matches("[A-Za-z_][A-Za-z0-9_]*")) {
                return false;
            }
        }
        return true;
    }

    public String getLastSegment() {        
        int lastDotIndex = value.lastIndexOf('.');
        if (lastDotIndex == -1) {
            // No dot means the whole value is the last segment
            return value;
        }
    
        return value.substring(lastDotIndex + 1);
    }
    
    
}
