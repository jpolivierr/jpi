package com.appvenir.utils;

import com.appvenir.core.PackageName;

public class StringUtils {
    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input; 
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String nextLine(String text) {
        return "\n" + text;
    }

    public static String nextDoubleLine(String text) {
        return "\n\n" + text;
    }

    public static String convertPackagetoPath(PackageName packageName)
    {
        return "/" + packageName.value().replaceAll("\\.", "/");
    }
}
