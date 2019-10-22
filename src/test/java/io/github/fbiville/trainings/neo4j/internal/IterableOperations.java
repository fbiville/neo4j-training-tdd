package io.github.fbiville.trainings.neo4j.internal;

public class IterableOperations {

    private IterableOperations() {
    }

    public static <T> T single(Iterable<T> allNodes) {
        return IteratorOperations.single(allNodes.iterator());
    }
}
