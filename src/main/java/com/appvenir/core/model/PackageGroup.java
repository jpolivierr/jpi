package com.appvenir.core.model;

import java.util.HashMap;
import java.util.Map;

public class PackageGroup {
    private String id;
    private Map<String, String> packages;

    public PackageGroup()
    {
        this.packages = new HashMap<>();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Map<String, String> getPackages() {
        return packages;
    }
    public void setPackages(Map<String, String>packages) {
        this.packages = packages;
    }

    @Override
    public String toString() {
        return "PackageGroup [id=" + id + ", packages=" + packages + "]";
    }
}
