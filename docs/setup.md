---
layout: page
title: Pré-requis
permalink: /setup/
order: 0
is_exercise: false
---

## Accès Internet

Un accès Internet est obligatoire pour résoudre les exercices
dans les meilleures conditions.

Cela vous permettra notamment de consulter la refcard [Cypher](https://neo4j.com/docs/cypher-refcard/current/) et de suivre les différents liens inclus dans les énoncés.

## Neo4j

### Téléchargement

Il est nécessaire d'installer [Neo4j](https://neo4j.com/download/). 
Au moment de l'écriture de ces lignes, la dernière version stable embarquée dans Neo4j Desktop est la version 3.5.8.

Bien que Neo4j Desktop embarque une version Enterprise, l'édition *community* est normalement suffisante pour les exercices.

Une fois Neo4j Desktop installé, vous pouvez créer et démarrer la base de données et accéder à son [interface de navigation](http://localhost:7474).

Notez qu'il vous sera demandé de changer de mot de passe à la première connexion. Il est important de s'en souvenir, car il sera utilisé dans des exercices ultérieurs.

### Validation

{% include graph_import.md %}


## Git

Installez le gestionnaire de version [Git](https://git-scm.com/).
Incluez ensuite le sous-répertoire `bin` dans le path existant.

Vérifiez, en ligne de commandes, que l'installation s'est bien déroulée (): 

```shell
➜  ~ git --version
git version 2.17.2
```

### Configuration d'un proxy

Si l'accès Internet est limité par un proxy d'entreprise, le plus simple est de suivre les étapes décrites dans cette
[réponse StackOverflow](http://stackoverflow.com/a/19213999/277128).

### Récupération du projet

En ligne de commandes, il vous suffit d'exécuter :

```shell
➜  ~ cd /le/chemin/que/vous/voulez
➜  ~ git clone --branch core_api https://github.com/graph-labs/neo4j-training-tdd.git
```

## JDK 11

Installez la dernière version du [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html) (et non JRE).

Une fois le binaire téléchargé et exécuté :

1. définissez une variable d'environnement `JAVA_HOME` dont la valeur correspond au répertoire où le JDK est installé
1. incluez le chemin vers le sous-répertoire `bin` au path existant.

Vous pouvez valider votre installation en vérifiant, en ligne de commandes, que les versions des exécutables `java` et `javac` sont les mêmes, comme dans l'exemple qui suit :

```shell
➜  ~ javac -version
javac 11
➜  ~ java -version
java version "11" 2018-09-25
Java(TM) SE Runtime Environment 18.9 (build 11+28)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11+28, mixed mode)
```

## Maven

Installez la dernière version de [Maven](https://maven.apache.org/download.cgi).

À l'instar du JDK, il est nécessaire d'inclure le chemin vers sous-répertoire `bin` dans le path existant.

Pour valider votre installation, vérifiez, en ligne de commandes, que votre version de Maven utilise le bon JDK, comme dans l'exemple qui suit :

```shell
➜  ~ mvn -version
Apache Maven 3.6.0 (97c98ec64a1fdfee7767ce5ffb20918da4f719f3; 2018-10-24T20:41:47+02:00)
Maven home: /usr/local/Cellar/maven-deluxe/3.6.0-0/libexec
Java version: 11, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home
Default locale: en_FR, platform encoding: UTF-8
OS name: "mac os x", version: "10.14.1", arch: "x86_64", family: "mac"
```

### Configuration globale
Si le fichier de configuration globale de Maven, `settings.xml`, dans le répertoire `.m2` situé dans le répertoire "home" de l'utilisateur courant existe déjà, renommez le temporairement et créer un fichier `settings.xml` avec le contenu minimal suivant :

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
</settings>
```

### Configuration d'un proxy

Si l'accès Internet est limité par un proxy d'entreprise, 
référez-vous à la [documentation suivante](https://maven.apache.org/guides/mini/guide-proxies.html) pour le configurer
avec Maven, en augmentant le fichier `settings.xml` décrit précédemment.

### Validation

À ce stade, vous pouvez exécuter en ligne de commandes :

```shell
➜  ~ cd /chemin/vers/neo4j-training
➜  ~ mvn -q
```

Vous devriez avoir alors un ensemble de tests en échec (cf. ci-dessous). Reste maintenant à les résoudre !

```shell
[...]
Results :

Failed tests:   should_extract_all_first_names(io.github.fbiville.trainings.neo4j._0_basics.BasicsTest): You should fix the array declaration
  should_perform_addition(io.github.fbiville.trainings.neo4j._0_basics.BasicsTest): You should fix the addition
  should_fail_dividing_by_zero(io.github.fbiville.trainings.neo4j._0_basics.BasicsTest): (..)
  should_contain_all_specified_numbers(io.github.fbiville.trainings.neo4j._0_basics.BasicsTest): You should fix the array declaration
  writes_node_with_several_labels_and_properties(io.github.fbiville.trainings.neo4j._1_core_api._1_NodeWriteTest): You should create a node and add several properties and labels to it
  removes_a_property(io.github.fbiville.trainings.neo4j._1_core_api._1_NodeWriteTest): You should remove the node property
  writes_node_with_a_single_label(io.github.fbiville.trainings.neo4j._1_core_api._1_NodeWriteTest): You should create a node with a single label
  writes_node_with_properties(io.github.fbiville.trainings.neo4j._1_core_api._1_NodeWriteTest): You should add several properties to the node
  writes_node_with_several_labels(io.github.fbiville.trainings.neo4j._1_core_api._1_NodeWriteTest): You should create a node with several label
  writes_simple_node(io.github.fbiville.trainings.neo4j._1_core_api._1_NodeWriteTest): You should create a simple node
  deletes_a_node(io.github.fbiville.trainings.neo4j._1_core_api._1_NodeWriteTest): You should delete the node
  should_update_the_relationship_property(io.github.fbiville.trainings.neo4j._1_core_api._2_RelationshipWriteTest): You should overwrite the relationship property
  should_create_relationship_between_two_new_nodes(io.github.fbiville.trainings.neo4j._1_core_api._2_RelationshipWriteTest): You should create a relationship between two new nodes
  should_create_relationship_with_properties(io.github.fbiville.trainings.neo4j._1_core_api._2_RelationshipWriteTest): You should create a relationship with properties between the two nodes
  should_remove_a_relationship(io.github.fbiville.trainings.neo4j._1_core_api._2_RelationshipWriteTest): You should remove the relationship
  should_create_relationship_between_existing_nodes(io.github.fbiville.trainings.neo4j._1_core_api._2_RelationshipWriteTest): You should create a relationship between the two nodes
  should_find_all_villains(io.github.fbiville.trainings.neo4j._1_core_api._3_GraphReadTest): You should find all villains
  should_find_all_heroes(io.github.fbiville.trainings.neo4j._1_core_api._3_GraphReadTest): You should find all heroes
  should_find_characters_appearing_in_first_opus(io.github.fbiville.trainings.neo4j._1_core_api._3_GraphReadTest): You should find all character nodes appearing in the first opus
  should_find_all_relationship_types(io.github.fbiville.trainings.neo4j._1_core_api._3_GraphReadTest): You should find all relationships
  should_find_largo_by_label_and_property(io.github.fbiville.trainings.neo4j._1_core_api._3_GraphReadTest): You should find a Largo LaGrande node
  should_find_all_character_nodes(io.github.fbiville.trainings.neo4j._1_core_api._3_GraphReadTest): You should find all nodes
  should_count_name_properties(io.github.fbiville.trainings.neo4j._1_core_api._4_TriggerTest): expected:<[2]L> but was:<[0]L>

Tests run: 23, Failures: 23, Errors: 0, Skipped: 0

[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test (default-test) on project neo4j-training: There are test failures.
[ERROR]
[ERROR] Please refer to /private/tmp/neo4j-training/target/surefire-reports for the individual test results.
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
```

## Installation d'Intellij Ultimate

Afin de bénéficier de l'auto-complétion et d'un confort d'édition optimal, il est recommandé d'installer l'IDE de Jetbrains, [Intellij Ultimate Edition](https://www.jetbrains.com/idea/). 

Si vous voulez briller en société et accessoirement devenir plus efficace lorsque vous écrivez du code, il est fortement conseillé d'apprendre les [raccourcis clavier](https://resources.jetbrains.com/assets/products/intellij-idea/IntelliJIDEA_ReferenceCard.pdf).

### Import du projet 

Il suffit ensuite d'importer le projet en sélectionnant le fichier `pom.xml` à la racine du répertoire `neo4j-training-tdd`. Il sera éventuellement demandé de spécifier un JDK, il suffit de suivre les étapes et il le localisera alors automatiquement.

À partir de ce point, il vous suffit maintenant de lancer n'importe quel test (cf. `src/test/java`) en suivant cette étape :

 1. clic droit sur l'un des fichiers `*Test.java`
 2. `Run`

Une console devrait alors apparaître avec le résultat de l'exécution !
