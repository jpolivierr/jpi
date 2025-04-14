package com.appvenir.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class Schemas {
    private List<PathLayout> pathLayouts;
    private Map<String, String> packages;

    public Schemas() {
        this.pathLayouts = new ArrayList<>();
        this.packages = new HashMap<>();
    }

    public Optional<PathLayout> getPathLayout(String id) {
        for (PathLayout pathLayout : pathLayouts) {
            if (pathLayout.getId().equals(id)) {
                return Optional.of(pathLayout);
            }
        }
        return Optional.empty();
    }

    public List<PathLayout> getPathLayouts() {
        return pathLayouts;
    }

    public void setPathLayouts(List<PathLayout> pathLayouts) {
        this.pathLayouts = pathLayouts;
    }

    public Map<String, String> getPackages() {
        return packages;
    }

    public void setPackages(Map<String, String> packages) {
        this.packages = packages;
    }
}

