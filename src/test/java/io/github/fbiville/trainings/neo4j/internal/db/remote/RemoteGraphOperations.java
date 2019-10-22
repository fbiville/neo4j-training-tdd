package io.github.fbiville.trainings.neo4j.internal.db.remote;

import io.github.fbiville.trainings.neo4j.internal.IterableOperations;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import java.util.List;
import java.util.Map;

public class RemoteGraphOperations {

    private final String graphBoltUri;

    public RemoteGraphOperations(String graphBoltUri) {
        this.graphBoltUri = graphBoltUri;
    }

    public Map<String, Object> getSingleNode() {
        return IterableOperations.single(getAllNodes());
    }

    public Iterable<Map<String, Object>> getAllNodes() {
        try (Driver driver = GraphDatabase.driver(graphBoltUri, AuthTokens.none());
             Session session = driver.session()) {

            StatementResult result = session.run("MATCH (n) RETURN n");
            return result.list(Record::asMap);
        }
    }

    public Map<String, Object> getSingleRelationship() {
        return IterableOperations.single(getAllRelationships());
    }

    public List<Map<String, Object>> getAllRelationships() {
        try (Driver driver = GraphDatabase.driver(graphBoltUri, AuthTokens.none());
             Session session = driver.session()) {

            StatementResult result = session.run("MATCH ()-[r]->() RETURN r");
            return result.list(Record::asMap);
        }
    }
}
