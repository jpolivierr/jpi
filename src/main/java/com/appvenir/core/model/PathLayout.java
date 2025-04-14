package com.appvenir.core.model;

import java.util.ArrayList;
import java.util.List;

public class PathLayout {
    private String id;
    private List<String> paths;

    public PathLayout()
    {
        this.paths = new ArrayList<>();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<String> getPaths() {
        return paths;
    }
    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    @Override
    public String toString() {
        return "PathLayout [id=" + id + ", paths=" + paths + "]";
    }
}
