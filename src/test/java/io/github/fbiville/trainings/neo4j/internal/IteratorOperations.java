package io.github.fbiville.trainings.neo4j.internal;

import java.util.Iterator;

public class IteratorOperations {

    private IteratorOperations() {
    }

    public static <T> T single(Iterator<T> iterator) {
        if (!iterator.hasNext()) {
            throw new RuntimeException("Expected 1 item, got 0");
        }
        T result = iterator.next();
        if (iterator.hasNext()) {
            throw new RuntimeException("Expected 1 item, got more");
        }
        return result;
    }
}
