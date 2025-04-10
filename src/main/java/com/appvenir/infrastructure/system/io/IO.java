package com.appvenir.infrastructure.system.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import com.appvenir.infrastructure.system.logger.Logger;
import com.appvenir.utils.Condition;

public class IO {

    public static String getFileContent(String filePath)
    {
        Condition.notNull(filePath, "FilePath cannot be null");
        Path path = Paths.get(filePath);
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = Files.newBufferedReader(path)) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                content.append(line).append("\n");
            }
            content.toString();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        return content.toString();
    }

    public static void createPathIfNotExist(String path) throws IOException
    {
        Condition.notNull(path, "Path cannot be null");
        Path filePath = Paths.get(path).normalize();
        Files.createDirectories(filePath);
    }

    public static void deletePath(String path) throws IOException
    {
        Condition.notNull(path, "Path cannot be null");
        Path filePath = Paths.get(path).normalize();
        Files.deleteIfExists(filePath);
    }

    public static String getFileInputStream(String filePath)
    {
        Condition.notNull(filePath, "FilePath cannot be null");
        Path path = Paths.get(filePath);
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = Files.newBufferedReader(path)) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                content.append(line).append("\n");
            }
            content.toString();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        return content.toString();
    }

    public static void writeToFile(String filePath, String content) throws IOException
    {
        Condition.notNull(filePath, "FilePath cannot be null");
        Condition.notNull(content, "Content cannot be null");
        Path path = Paths.get(filePath);

        try(BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) 
        {
            writer.write(content);
        }
        catch(AccessDeniedException ex)
        {
            throw new IOException("Access denied for file path: " + filePath);
        }
        catch(NoSuchFileException ex)
        {
            throw new IOException("Invalid file path: " + filePath);
        }
    }

    public static boolean pathExists(String filePath)
    {
        Condition.notNull(filePath, "FilePath cannot be null");
        Path path = Paths.get(filePath);

        return Files.exists(path);
    }

    public static List<String> getFileNamesFromDir(String dirPath) {
        Path currentPath = Paths.get(dirPath);
        try (Stream<Path> paths = Files.list(currentPath)) {
            return paths.filter(Files::isRegularFile)
                    .map(path -> path.getFileName().toString())
                    .toList();
        } catch (IOException e) {
            Logger.error("No files found in " + dirPath);
            return Collections.emptyList();
        }
    }

    public static String getFileExtension(String fileName) {
        Path path = Paths.get(fileName);
        String name = path.getFileName().toString();
        int lastDot = name.lastIndexOf(".");
        if (lastDot == -1 || lastDot == name.length() - 1) {
            return ""; // No extension
        }
        return name.substring(lastDot + 1);
    }

    public static String getFileExtension(Path fileName) {
        String name = fileName.getFileName().toString();
        int lastDot = name.lastIndexOf(".");
        if (lastDot == -1 || lastDot == name.length() - 1) {
            return ""; // No extension
        }
        return name.substring(lastDot + 1);
    }
}
