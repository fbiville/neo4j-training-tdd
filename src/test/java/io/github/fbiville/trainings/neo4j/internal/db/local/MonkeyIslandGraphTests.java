package io.github.fbiville.trainings.neo4j.internal.db.local;

import io.github.fbiville.trainings.neo4j.internal.IteratorOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

import java.util.logging.LogManager;

import static org.assertj.core.api.Assertions.assertThat;

public class MonkeyIslandGraphTests extends EmptyGraphTests {


    @BeforeEach
    public void prepareAll() {
        try (Transaction transaction = graphDb.beginTx()) {
            graphDb.execute("CREATE \t(vg:`Video game` {title: 'Monkey Island'}),\n" +
                    "        (gt:Character:Hero {name:'Guybrush Threepwood', in_first_opus: true}),\n" +
                    "        (em:Character:Hero {name:'Elaine Marley', in_first_opus: true}),\n" +
                    "        (lc:Character:Villain {name: 'LeChuck', in_first_opus: true}),\n" +
                    "        (ll:Character:Villain {name: 'Largo LaGrande', in_first_opus: false}),\n" +
                    "        (gt)-[:APPEARS_IN]->(vg),\n" +
                    "        (gt)-[:LOVES]->(em),\n" +
                    "        (gt)-[:FIGHTS]->(lc),\n" +
                    "        (gt)-[:FIGHTS]->(ll),\n" +
                    "        (ll)-[:HATES]->(gt),\n" +
                    "        (ll)-[:APPEARS_IN]->(vg),\n" +
                    "        (lc)-[:LOVES]->(em),\n" +
                    "        (lc)-[:HATES]->(gt),\n" +
                    "        (lc)-[:APPEARS_IN]->(vg),\n" +
                    "        (em)-[:HATES]->(lc),\n" +
                    "        (em)-[:APPEARS_IN]->(vg)");
            transaction.success();
        }
    }

    @Test
    @DisplayName("makes sure the database properly starts")
    void sanity_check() {
        try (Transaction ignored = graphDb.beginTx()) {
            Result result = graphDb.execute("MATCH (n) RETURN count(n) AS count");
            Long actualCount = IteratorOperations.single(result.<Long>columnAs("count"));
            long expectedCount = 5;
            assertThat(actualCount)
                    .overridingErrorMessage("Expected %d nodes in Monkey Island graph, got %d", expectedCount, actualCount)
                    .isEqualTo(expectedCount);
        }
    }
}
