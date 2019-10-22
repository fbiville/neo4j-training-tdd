package io.github.fbiville.trainings.neo4j._0_basics;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * This class is just a warm-up ;-)
 */
@DisplayName("Getting started with assertions")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BasicsTest {

    @Test
    @DisplayName("... on basic arithmetic operations")
    @Order(1)
    void should_perform_addition() {
        int five = 2 + 3;

        // please do not change the assertion, it describes the desired result is!
        assertThat(five).isEqualTo(5);
    }

    @Test
    @DisplayName("... on Java collections")
    @Order(2)
    void should_contain_all_specified_numbers() {
        List<Integer> ints = Arrays.asList(1, 2, 3);

        // please do not change the assertion, it describes the desired result is!
        assertThat(ints).containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("... on collection extractions")
    @Order(3)
    void should_extract_all_first_names() {
        List<Person> persons = Arrays.asList(
                new Person("Peter", "Neubauer"),
                new Person("Emil", "Eifrem"));

        // please do not change the assertion, it describes the desired result is!
        assertThat(persons)
                .extracting(Person::getFirstName)
                .containsExactly("Peter", "Emil");
    }

    @Test
    @DisplayName("... on failure assertions")
    @Order(4)
    void should_fail_dividing_by_zero() {
        assertThatThrownBy(() -> {
            int denominator = 0;
            double ignored = 1 / denominator;
        }).isInstanceOf(ArithmeticException.class).hasMessageContaining("/ by zero");
    }

    private static class Person {
        private final String firstName;
        private final String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            final Person other = (Person) obj;
            return Objects.equals(this.firstName, other.firstName)
                    && Objects.equals(this.lastName, other.lastName);
        }
    }

}
