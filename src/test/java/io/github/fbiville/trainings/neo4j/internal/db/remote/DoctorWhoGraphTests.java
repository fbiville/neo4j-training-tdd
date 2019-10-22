package io.github.fbiville.trainings.neo4j.internal.db.remote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import java.util.logging.LogManager;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class DoctorWhoGraphTests {

    static {
        LogManager.getLogManager().reset();
    }

    @Container
    private Neo4jContainer neo4jContainer = new Neo4jContainer()
            .withAdminPassword(null)
            .withDatabase(MountableFile.forClasspathResource("/doctor-who-db"));

    protected RemoteGraphOperations graphOperations;

    @BeforeEach
    public void prepareAll() {
        graphOperations = new RemoteGraphOperations(neo4jContainer.getBoltUrl());
    }

    @Test
    @DisplayName("Makes sure the database properly starts")
    void sanity_check() {
        try (Driver driver = GraphDatabase.driver(neo4jContainer.getBoltUrl(), AuthTokens.none());
             Session session = driver.session()) {

            StatementResult result = session.run("MATCH (n) RETURN COUNT(n) AS count");
            long actualCount = result.single().get("count").asLong();
            long expectedCount = 1063L;
            assertThat(actualCount)
                    .overridingErrorMessage("Expected %d nodes in Dr Who graph, got %d", expectedCount, actualCount)
                    .isEqualTo(expectedCount);
        }
    }
}
