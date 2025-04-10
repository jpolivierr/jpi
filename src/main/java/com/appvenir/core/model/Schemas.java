package com.appvenir.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class Schemas {
    private List<DirSchema> dirSchemas;
    private HashMap<String, String> classNameFormat;

    public Schemas()
    {
        this.dirSchemas = new ArrayList<>();
        this.classNameFormat = new HashMap<>();
    }

    public Optional<DirSchema> getDirSchema(String id)
    {
        for(DirSchema dirSchema : dirSchemas)
        {
            if(dirSchema.getId().equals(id))
            {
                return Optional.of(dirSchema);
            }
        }
        return Optional.empty();
    }

    public List<DirSchema> getDirSchemas() {
        return dirSchemas;
    }
    public void setDirSchema(List<DirSchema> dirSchema) {
        this.dirSchemas = dirSchema;
    }
    public HashMap<String, String> getClassNameFormat() {
        return classNameFormat;
    }
    public void setClassNameFormat(HashMap<String, String> classNameFormat) {
        this.classNameFormat = classNameFormat;
    }
}
