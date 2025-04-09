package com.appvenir.core.javaFile;

import lombok.Getter;

@Getter
public class TypeVariable {
    private String accessModifier;
    private Object type;
    private String name;
    private boolean isFinal = false;
    private boolean isStatic = false;

    private TypeVariable(AccessModifier accessModifier, Object type, String name)
    {
        this.accessModifier = accessModifier.name().toLowerCase();
        this.type = type;
        this.name = name;
    }

    public static TypeVariable newInstant(AccessModifier accessModifier, Object type, String name)
    {
        return new TypeVariable(accessModifier, type, name);
    }


    public TypeVariable makeStatic()
    {
        this.isStatic = true;
        return this;
    }

    public TypeVariable makeFinal()
    {
        this.isFinal = true;
        return this;
    }

    public String getStructuredOutput()
    {
        StringBuilder content = new StringBuilder();
        content.append(this.accessModifier);
        if(isFinal())
        {
            content.append(" final");
        }
        if(isStatic())
        {
            content.append(" static");
        }
        content.append(" ").append(this.type.getClass().getSimpleName());
        content.append(" ").append(this.name);
        return content.toString();
    }
}
