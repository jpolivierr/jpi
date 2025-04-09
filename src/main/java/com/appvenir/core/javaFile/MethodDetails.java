package com.appvenir.core.javaFile;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class MethodDetails {
    private boolean isStatic;
    private String name;
    private AccessModifier AccessModifier;
    private List<TypeVariable> parameters = new ArrayList<>();
    private List<String> statements = new ArrayList<>();
}
