package com.appvenir.core.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Schemas {
    private List<DirSchema> dirSchema = new ArrayList<>();

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    static class DirSchema {
        private String id;
        private List<String> paths = new ArrayList<>();
    }
}
