package com.appvenir.core.javaFile;

import com.appvenir.core.javaFile.utils.JavaFileUtils;
import com.appvenir.utils.Condition;

public record PackageName(String name) {

    public PackageName {
        Condition.notNullOrEmpty(name, "Name");
        if(!JavaFileUtils.isValidJavaPackageName(name))
        {
            throw new IllegalArgumentException("Not a valid package name: " + name);
        }
    }
}
