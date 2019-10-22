package io.github.fbiville.trainings.neo4j.internal.db.local;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import static io.github.fbiville.trainings.neo4j.internal.IterableOperations.single;

public class LocalGraphOperations {
    private final GraphDatabaseService graphDb;

    public LocalGraphOperations(GraphDatabaseService graphDb) {
        this.graphDb = graphDb;
    }

    public Node getSingleNode() {
        return single(getAllNodes());
    }

    public Iterable<Node> getAllNodes() {
        return graphDb.getAllNodes();
    }

    public Relationship getSingleRelationship() {
        return single(getAllRelationships());
    }

    public Iterable<Relationship> getAllRelationships() {
        return graphDb.getAllRelationships();
    }

    public String getSingleLabelName(Node node) {
        return single(node.getLabels()).name();
    }
}
