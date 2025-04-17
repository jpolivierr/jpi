package com.appvenir.system.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class IOTest {
    private static final String TEST_DIR = "build/test-dir";

    @Test
    public void should_create_path_if_not_exists(@TempDir Path tempDir) throws IOException
    {
        Path testPath = tempDir.resolve(TEST_DIR + "/user/preferences");
        IO.createPathIfNotExist(testPath.toString());

        assertTrue(Files.exists(testPath));
        assertTrue(Files.isDirectory(testPath));
    }

    @Test
    public void should_throw_exception_when_path_is_null()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            IO.createFileIfNotExist(null);
        });
    }

    @Test
    public void should_create_a_file_if_it_does_not_exits(@TempDir Path tempDir) throws IOException
    {
        Path tempPath = tempDir.resolve(TEST_DIR);
        String filePath = tempPath.toString() + "/demo.txt";

        IO.createFileIfNotExist(filePath);

        Path newFilePath = Paths.get(filePath);
        assertTrue(Files.exists(newFilePath));
        assertTrue(Files.isRegularFile(newFilePath));
        assertEquals("demo.txt", newFilePath.getFileName().toString());
    }
    
}
