package com.appvenir.core.javaFile;

import java.util.List;

import com.appvenir.core.javaFile.ClassDetails.ConstructorDetails;


public interface TypeSpecification {
    public PackageName getPackageName();
    public AccessModifier getAccessModifier();
    public boolean isStatic();
    public String getName();
    public ConstructorDetails getConstructor();
    public List<MethodDetails> getMethods();
    public String getStructuredOutput();
}
