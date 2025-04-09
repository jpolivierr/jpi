package com.appvenir.core.javaFile;

import java.util.ArrayList;
import java.util.List;
import com.appvenir.utils.Condition;
import com.appvenir.utils.StringUtils;

import lombok.Getter;

@Getter
public class ClassDetails implements TypeSpecification{
    private final String name;
    private final PackageName packageName;
    private AccessModifier accessModifier = AccessModifier.PUBLIC;
    private boolean isStatic;
    private List<TypeVariable> properties;
    private ConstructorDetails constructor;
    private List<MethodDetails> methods;

    public ClassDetails(String name, PackageName packageName)
    {
        Condition.notNullOrEmpty(name, "name");
        this.name =  StringUtils.capitalizeFirstLetter(name);
        this.packageName = packageName;
        this.properties = new ArrayList<>();
    }

    public void addProperty(TypeVariable variable)
    {
        Condition.notNull(variable, "Variable");
        properties.add(variable);
    }

    @Getter
    class ConstructorDetails {
        private String name;
        private AccessModifier AccessModifier;
        private List<TypeVariable> parameters = new ArrayList<>();
        private List<String> statements = new ArrayList<>();
    }

    @Override
    public String getStructuredOutput()
    {
        StringBuilder content = new StringBuilder();
        content
            .append(this.accessModifier.name().toLowerCase())
            .append(" class ")
            .append(this.name)
            .append("{}");
        return content.toString();
    }
}
