package com.appvenir.valueObjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.appvenir.core.valueObjects.PackageName;
import com.appvenir.exceptions.InvalidPackageNameException;

public class PackageNameTest {

    @Test
    void should_create_a_valid_package_name() {
        assertEquals("com", PackageName.create("com").value());
        assertEquals("com.appvenir", PackageName.create("com.appvenir").value());
        assertEquals("com.appvenir.user", PackageName.create("coM.appveniR.User").value());
    }

    @Test
    void should_throw_InvalidPackageNameException() {

        InvalidPackageNameException ex1 = assertThrows(InvalidPackageNameException.class, () -> {
            PackageName.create("123utils");
        });
        assertEquals("Invalid package name: 123utils", ex1.getMessage());

        InvalidPackageNameException ex2 = assertThrows(InvalidPackageNameException.class, () -> {
            PackageName.create("com..example");
        });
        assertEquals("Invalid package name: com..example", ex2.getMessage());

        InvalidPackageNameException ex3 = assertThrows(InvalidPackageNameException.class, () -> {
            PackageName.create("com.example@dev");
        });
        assertEquals("Invalid package name: com.example@dev", ex3.getMessage());

        InvalidPackageNameException ex4 = assertThrows(InvalidPackageNameException.class, () -> {
            PackageName.create("com.example.my app");
        });
        assertEquals("Invalid package name: com.example.my app", ex4.getMessage());

        InvalidPackageNameException ex5 = assertThrows(InvalidPackageNameException.class, () -> {
            PackageName.create(".com.example");
        });
        assertEquals("Invalid package name: .com.example", ex5.getMessage());

        InvalidPackageNameException ex6 = assertThrows(InvalidPackageNameException.class, () -> {
            PackageName.create("com.example.");
        });
        assertEquals("Invalid package name: com.example.", ex6.getMessage());
    }

}
