package com.airtribe.meditrack.util;

import java.util.*;

public class DataStore<T> {

    private Map<String, T> store = new HashMap<>();

    public void add(String id, T obj) { store.put(id, obj); }
    public T get(String id) { return store.get(id); }
    public void remove(String id) { store.remove(id); }
    public List<T> getAll() { return new ArrayList<>(store.values()); }
}