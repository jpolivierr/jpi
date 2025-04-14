package com.appvenir.core.model;

import java.util.ArrayList;
import java.util.List;

public class PackageGroup {
    private String id;
    private List<String> packages;

    public PackageGroup()
    {
        this.packages = new ArrayList<>();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<String> getPackages() {
        return packages;
    }
    public void setPackages(List<String> packages) {
        this.packages = packages;
    }

    @Override
    public String toString() {
        return "PackageGroup [id=" + id + ", packages=" + packages + "]";
    }
}
