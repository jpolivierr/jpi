package com.appvenir.valueObjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.appvenir.core.valueObjects.JavaClassName;
import com.appvenir.exceptions.InvalidJavaClassNameException;

public class JavaClassNameTest {

    @Test
    void should_return_a_valid_java_file_path() {
        assertEquals("/User.java", JavaClassName.create("user").getFilePath());
    }

    @Test
    void should_return_a_valid_java_file_name() {
        assertEquals("User.java", JavaClassName.create("user").getFileName());
    }

    @Test
    void should_Create_a_valid_JavaClassName() {
        assertEquals("User", JavaClassName.create("user").value());
        assertEquals("_user", JavaClassName.create("_user").value());
    }

    @Test
    void should_throw_InvalidJavaClassName() {
        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create("class");
        });

        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create("1User");
        });

        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create("123Class");
        });

        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create("My-Class");
        });

        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create("@User");
        });

        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create("My Class");
        });

        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create("User!");
        });

        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create("user*");
        });

        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create(null);
        });

        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create("");
        });

        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create("User.");
        });

        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create(".User");
        });

        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create("new User");
        });

        assertThrows(InvalidJavaClassNameException.class, () -> {
            JavaClassName.create("@interface");
        });
    }
}
