package io.github.fbiville.trainings.neo4j._1_core_api;

import io.github.fbiville.trainings.neo4j.internal.db.local.MonkeyIslandGraphTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

/**
 * This class focuses on the basic read operations.
 */
@DisplayName("Getting familiar with core API read operations")
public class _3_GraphReadTest extends MonkeyIslandGraphTests {

    @Test
    @DisplayName("find all nodes with a specific label")
    @Order(131)
    public void should_find_all_character_nodes() {
        try (Transaction ignored = graphDb.beginTx()) {
            Iterator<Node> allNodes = graphDb.findNodes(Label.label("Character"));

            assertThat(allNodes)
                    .hasSize(4)
                    .extracting(node -> (String) node.getProperty("name"))
                    .containsOnly("Guybrush Threepwood", "Elaine Marley", "LeChuck", "Largo LaGrande");
        }
    }

    @Test
    @DisplayName("find all relationship types")
    @Order(132)
    public void should_find_all_relationship_types() {
        try (Transaction ignored = graphDb.beginTx()) {
            Iterable<RelationshipType> allRelationships = graphDb.getAllRelationshipTypesInUse();

            assertThat(allRelationships)
                    .hasSize(4)
                    .extracting(RelationshipType::name)
                    .containsOnly("LOVES", "FIGHTS", "HATES", "APPEARS_IN");
        }
    }

    @Test
    @DisplayName("find specific node by label and property")
    @Order(133)
    public void should_find_largo_by_label_and_property() {
        try (Transaction ignored = graphDb.beginTx()) {
            Node largo = graphDb.findNode(Label.label("Character"), "name", "Largo LaGrande");

            assertThat(largo.getLabels())
                    .extracting(Label::name)
                    .containsOnly("Character", "Villain");
            assertThat(largo.getAllProperties()).containsOnly(
                    entry("name", "Largo LaGrande"),
                    entry("in_first_opus", false)
            );
            assertThat(largo.getRelationships())
                    .extracting(Relationship::getType)
                    .extracting(RelationshipType::name)
                    .containsOnly("FIGHTS", "HATES", "APPEARS_IN");
        }
    }

    @Test
    @DisplayName("find specific node*s* by label and property")
    @Order(134)
    public void should_find_characters_appearing_in_first_opus() {
        try (Transaction ignored = graphDb.beginTx()) {
            Iterator<Node> firstOpusCharacters = graphDb.findNodes(Label.label("Character"), "in_first_opus", true);

            assertThat(firstOpusCharacters)
                    .extracting(node -> (String) node.getProperty("name"))
                    .containsOnly("Guybrush Threepwood", "Elaine Marley", "LeChuck");
        }
    }
}
