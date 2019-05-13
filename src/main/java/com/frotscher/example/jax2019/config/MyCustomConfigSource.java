package com.frotscher.example.jax2019.config;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyCustomConfigSource implements ConfigSource {

    private static Map<String, String> myDatabase = new HashMap<>();

    static {
        myDatabase.put("VALUE_FROM_DB_1", "abc");
        myDatabase.put("VALUE_FROM_DB_2", "def");
        myDatabase.put("VALUE_FROM_DB_3", "efg");
    }

    @Override
    public int getOrdinal() {
        return 1; // HIGHEST
    }

    @Override
    public Set<String> getPropertyNames() {
        return myDatabase.keySet();
    }

    @Override
    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(myDatabase);
    }

    @Override
    public String getValue(String key) {
        return myDatabase.get(key);
    }

    @Override
    public String getName() {
        return "customDbConfig";
    }

}
