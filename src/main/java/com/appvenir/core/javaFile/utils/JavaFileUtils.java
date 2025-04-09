package com.appvenir.core.javaFile.utils;

import java.util.regex.Pattern;

import com.appvenir.core.javaFile.PackageName;
import com.appvenir.utils.Condition;

public class JavaFileUtils {
    
    public static String packageName(PackageName packageName)
    {
        Condition.notNull(packageName, "PackageName cannot be null");
        return "package " + packageName.name() + ";";
    }

    public static String importName(PackageName packageName)
    {
        Condition.notNull(packageName, "PackageName cannot be null");
        return "import " + packageName.name() + ";";
    }

    public static String fileName(String fileName)
    {
        Condition.notNullOrEmpty(fileName, "File name");
        if(fileName.endsWith(".java"))
        {
            return fileName;
        }
        return fileName + ".java";
    }

    public static boolean isValidJavaPackageName(String packageName) {
        final Pattern PACKAGE_PATTERN = Pattern.compile("^[a-z][a-z0-9]*((\\.[a-z][a-z0-9]*)*)$");
        if (packageName == null || packageName.isEmpty()) {
            return false;
        }
        return PACKAGE_PATTERN.matcher(packageName).matches();
    }
}
