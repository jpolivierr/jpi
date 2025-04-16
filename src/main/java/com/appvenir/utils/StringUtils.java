package com.appvenir.utils;

import com.appvenir.core.valueObjects.PackageName;

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

    public static String getLastPackageSegment(PackageName packageName) 
    {
        String packageValue = packageName.value();
        int lastDotIndex = packageValue.lastIndexOf(".");
        return lastDotIndex != -1 ? packageValue.substring(lastDotIndex + 1) : packageValue;
    }

    public static String addPackageIdToFileName(String fileName, String packageId)
    {
        String lastName = StringUtils.capitalizeFirstLetter(packageId);
        return StringUtils.capitalizeFirstLetter(fileName) + lastName;
    }

    public static String createJavaFile(String fileName)
    {
       return fileName + ".java";
    }

    public static PackageName replaceLastSegment(PackageName packageName, String newLastSegment) {
        String originalPackage = packageName.value();
        if (originalPackage == null || originalPackage.isEmpty()) {
            throw new IllegalArgumentException("Original package name cannot be null or empty.");
        }

        int lastDotIndex = originalPackage.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return PackageName.create(newLastSegment);
        }

        return PackageName.create(originalPackage.substring(0, lastDotIndex + 1) + newLastSegment);
    }
    
}
