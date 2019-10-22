package io.github.fbiville.trainings.neo4j.internal.db.local;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.io.TempDir;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmptyGraphTests {

    protected GraphDatabaseService graphDb;
    protected LocalGraphOperations graphOperations;

    @BeforeEach
    public void prepare(@TempDir File parentDir) {
        File storeDir = createSubdirectory(parentDir, "store"); // see #12133 in neo4j/neo4j
        graphDb = new TestGraphDatabaseFactory().newEmbeddedDatabase(storeDir);
        graphOperations = new LocalGraphOperations(graphDb);
    }

    private File createSubdirectory(@TempDir File parentDir, String subDir) {
        try {
            return Files.createDirectory(new File(parentDir, subDir).toPath()).toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
