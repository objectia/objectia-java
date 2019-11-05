package com.objectia.models;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class MultipartForm {
    protected Map<String, Object> properties;
    protected List<String> files;

    /**
     * Class constructor
     */
    public MultipartForm() {
        this.files = new ArrayList<>();
        this.properties = new HashMap<>();
    }

    public void addProperty(final String key, final Object value) {
        this.properties.put(key, value);
    }

    public void addFile(final String fileName) {
        this.files.add(fileName);
    }

    public List<String> getFiles() {
        return this.files;
    }

    public Map<String,Object> getProperties() {
        return this.properties;
    }
}