package com.appvenir.core.valueObjects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FileAttribute {
    private final long size;
    private final String ext;
    private final String name;
    private final boolean isDirectory;
    private final boolean isZip;
    private final boolean isJar;

    private FileAttribute(Path path) throws IOException {
        if(!Files.exists(path))
        {
            throw new IllegalArgumentException("File does not exists: " + path.toString());
        }
        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
        this.size = attrs.size();
        this.ext = getFileExtension(path);
        this.name = path.getFileName().toString();
        this.isDirectory = Files.isDirectory(path);
        this.isZip = name.endsWith(".zip");
        this.isJar = name.endsWith(".jar");
    }

    private String getFileExtension(Path path) {
        String name = path.getFileName().toString();
        int lastDot = name.lastIndexOf(".");
        if (lastDot == -1 || lastDot == name.length() - 1) {
            return null; // No extension
        }
        return name.substring(lastDot + 1);
    }

    public long getBytes() {
        return size;
    }

    public double getKilobytes() {
        return size / 1024.0;
    }

    public double getMegabytes() {
        return size / (1024.0 * 1024);
    }

    public double getGigabytes() {
        return size / (1024.0 * 1024 * 1024);
    }

    public long getSize() {
        return size;
    }

    public String getExt() {
        return ext;
    }

    public String getName() {
        return name;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public boolean isZip() {
        return isZip;
    }

    public boolean isJar() {
        return isJar;
    }

}
