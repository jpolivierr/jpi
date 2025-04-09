package com.appvenir.utils;

public class Condition {
    public static void notNull(Object object, String objectName)
    {
        if(object == null){
            throw new IllegalArgumentException(objectName + "cannot be null");
        };
    }

    public static void notBlank(String content, String objectName)
    {
        if(content != null && content.isBlank()){
            throw new IllegalArgumentException(objectName + "cannot be blank");
        };
    }

    public static void notNullOrEmpty(String content, String objectName)
    {
        if(content == null || content.isEmpty()){
            throw new IllegalArgumentException(objectName + "cannot be blank");
        };
    }
}
