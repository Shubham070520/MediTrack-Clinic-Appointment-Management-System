package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final IdGenerator INSTANCE = new IdGenerator();
    private AtomicInteger counter = new AtomicInteger(1);

    private IdGenerator() {}

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public String generateId() {
        return "ID" + counter.getAndIncrement();
    }
}