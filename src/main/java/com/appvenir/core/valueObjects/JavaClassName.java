package com.appvenir.core.valueObjects;

import com.appvenir.core.exceptions.InvalidJavaClassNameException;
import com.appvenir.utils.CliUtils;
import com.appvenir.utils.StringUtils;

public class JavaClassName {
    private final String value;

    private JavaClassName(String value)
    {
        throwsIfNotValidClassName(value);
        this.value = StringUtils.capitalizeFirstLetter(value);
    }

    public String getFileName()
    {
        return this.value + ".java";
    }

    public String getFilePath()
    {
        return "/" + this.value + ".java";
    }

    public String value()
    {
        return this.value;
    }

    public JavaClassName suffixed(String name)
    {
        return new JavaClassName(this.value + StringUtils.capitalizeFirstLetter(name));
    }

    public static JavaClassName create(String name)
    {
        return new JavaClassName(name);
    }

    private final void throwsIfNotValidClassName(String value)
    {
        if (!CliUtils.isValidJavaClassName(value)) 
        {
            throw new InvalidJavaClassNameException(value);
        }
    }
    
}


