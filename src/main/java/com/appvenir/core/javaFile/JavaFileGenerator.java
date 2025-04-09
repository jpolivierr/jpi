package com.appvenir.core.javaFile;
import com.appvenir.infrastructure.system.io.IO;
public class JavaFileGenerator {
    private final String filePath;
    private final JavaFileDetails javaFileDetails;
    private StringBuilder fileContent;

    public JavaFileGenerator(String filePath, JavaFileDetails javaFileDetails)
    {
        this.filePath = filePath;
        this.javaFileDetails = javaFileDetails;
        this.fileContent = new StringBuilder();
    }

    public void createFile()
    {
        fileContent.append(javaFileDetails.getPackageName()).append("\n");
    }

    public void createClass()
    {
        TypeSpecification type = javaFileDetails.getType();
        fileContent.append(type.getStructuredOutput());
        // String accessModifier = javaFileDetails.getTypeAccessModifier();
        // String className = javaFileDetails.getTypeName();
        // fileContent.append("\n").append(accessModifier).append(" ").append("class ").append(className).append("{}");

    }

    public void execute()
    {
        createFile();
        createClass();
        System.out.println(fileContent.toString());
        try {
            IO.writeToFile(filePath + javaFileDetails.getPath(), fileContent.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    

}
