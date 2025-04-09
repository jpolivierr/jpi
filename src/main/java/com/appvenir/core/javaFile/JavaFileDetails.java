package com.appvenir.core.javaFile;

import java.util.List;

import com.appvenir.core.javaFile.utils.JavaFileUtils;

import lombok.Getter;

@Getter
public class JavaFileDetails {
    private final String fileName;
    private final String packageName;
    private final TypeSpecification type;
    private List<TypeSpecification> dependencies;

    public JavaFileDetails(TypeSpecification typeSpecification)
    {
        this.type = typeSpecification;
        this.packageName = JavaFileUtils.packageName(typeSpecification.getPackageName());
        this.fileName = JavaFileUtils.fileName(typeSpecification.getName());
    }

    public String getTypeAccessModifier()
    {
        return this.type.getAccessModifier().name().toLowerCase();
    }

    public String getTypeName()
    {
        return this.type.getName();
    }

    public String getPath()
    {
        return "/" + fileName;
    }

}
