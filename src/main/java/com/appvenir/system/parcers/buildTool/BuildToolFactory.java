package com.appvenir.system.parcers.buildTool;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.appvenir.system.io.IO;
import com.appvenir.utils.Condition;

public class BuildToolFactory {

    public static Optional<BuildToolParser> getBuildToolParser(String file)
    {
        Condition.notNull(file, "File cannot be null");
        Path filePath = Paths.get(file);
        if(!Files.isRegularFile(filePath))
        {
            throw new IllegalStateException("Not a valid file: " + file);
        }

        String fileExt = IO.getFileExtension(filePath);
        return switch (fileExt) {
            case "xml" -> Optional.of(new MavenBuildToolParser());
            default -> Optional.empty();
        };
    }
    
}
