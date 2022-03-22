package hw8;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public abstract class GraphTest {

  protected Graph<String, String> graph;

  @BeforeEach
  public void setupGraph() {
    this.graph = createGraph();
  }

  protected abstract Graph<String, String> createGraph();

  @Test
  @DisplayName("insert(v) returns a vertex with given data")
  public void canGetVertexAfterInsert() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals(v1.get(), "v1");
  }

  @Test
  @DisplayName("insert(U, V, e) returns an edge with given data")
  public void canGetEdgeAfterInsert() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    assertEquals(e1.get(), "v1-v2");
  }

  @Test
  @DisplayName("insert(null, V, e) throws exception")
  public void insertEdgeThrowsPositionExceptionWhenFirstVertexIsNull() {
    try {
      Vertex<String> v = graph.insert("v");
      graph.insert(null, v, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  // TODO add more tests here.
  @Test
  @DisplayName("insert(null) throws exception")
  public void insertNullVertexThrowsInsertionException() {
    try {
      Vertex<String> v = graph.insert(null);
      fail("The expected exception was not thrown");
    } catch (InsertionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert duplicate vertices throws exception")
  public void insertDuplicateVerticesThrowsInsertionException() {
    try {
      Vertex<String> v = graph.insert("v1");
      Vertex<String> v1 = graph.insert("v1");
      fail("The expected exception was not thrown");
    } catch (InsertionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert self-loop edge throws exception")
  public void insertEdgeThrowsInsertionExceptionWhenSelfLoop() {
    try {
      Vertex<String> v = graph.insert("v");
      graph.insert(v, v, "e");
      fail("The expected exception was not thrown");
    } catch (InsertionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert duplicate edge throws exception")
  public void insertEdgeThrowsInsertionExceptionWhenDuplicateEdge() {
    try {
      Vertex<String> v = graph.insert("v");
      Vertex<String> v1 = graph.insert("v1");
      graph.insert(v, v1, "e");
      graph.insert(v, v1, "e1");
      fail("The expected exception was not thrown");
    } catch (InsertionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("remove(v) returns a vertex with given data")
  public void canGetVertexAfterRemove() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals(v1.get(), "v1");
    assertEquals("v1", graph.remove(v1));
  }

  @Test
  @DisplayName("remove(v) returns PositionException when removing invalid vertex")
  public void invalidVertexThrowsPositionExceptionAfterRemove() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals(v1.get(), "v1");
    try {
      graph.remove(v1);
      graph.remove(v1);
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("remove(v) returns RemovalException when removing vertex with edges")
  public void invalidVertexThrowsRemovalExceptionAfterRemove() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals(v1.get(), "v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    assertEquals(e1.get(), "v1-v2");
    try {
      graph.remove(v1);
      fail("The expected exception was not thrown");
    } catch (RemovalException ex) {
      return;
    }
  }

  @Test
  @DisplayName("remove(e) returns an edge with given data")
  public void canGetEdgeAfterRemove() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals(v1.get(), "v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    assertEquals(e1.get(), "v1-v2");
    assertEquals("v1-v2", graph.remove(e1));
  }

  @Test
  @DisplayName("remove(e) returns PositionException when removing invalid edge")
  public void invalidEdgeThrowsPositionExceptionAfterRemove() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals(v1.get(), "v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    assertEquals(e1.get(), "v1-v2");
    try {
      graph.remove(e1);
      graph.remove(e1);
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("vertex can be correctly labelled and the label can be obtained")
  public void labelTestForVertices() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals(v1.get(), "v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.label(v1, "3");
    assertEquals("3", graph.label(v1));
    graph.label(v2, "5");
    assertEquals("5", graph.label(v2));
  }

  @Test
  @DisplayName("edge can be correctly labelled and the label can be obtained")
  public void labelTestForEdges() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals(v1.get(), "v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v = graph.insert("v");
    Edge<String> e = graph.insert(v, v1, "e");
    Edge<String> e1 = graph.insert(v1, v2, "e1");
    graph.label(e1, "3");
    assertEquals("3", graph.label(e1));
    graph.label(e, "5");
    assertEquals("5", graph.label(e));
  }

  @Test
  @DisplayName("invalid input in label() throws PositionException")
  public void labelTestThrowsPositionException() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals(v1.get(), "v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v = graph.insert("v");
    assertEquals("v1", graph.remove(v1));
    Edge<String> e = graph.insert(v, v2, "e");
    graph.label(e, "5");
    assertEquals("5", graph.label(e));
    try {
      Edge<String> e1 = graph.insert(v, v1, "e1");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Iterable vertices() test")
  public void iterableVerticesTest() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    List<Vertex<String>> vertices = new LinkedList<>();
    vertices.add(v);
    vertices.add(v1);
    vertices.add(v2);
    assertEquals(vertices, graph.vertices());
  }

  @Test
  @DisplayName("Iterable edges() test")
  public void iterableEdgesTest() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v, v1, "e");
    Edge<String> e1 = graph.insert(v, v2, "e1");
    Edge<String> e2 = graph.insert(v1, v2, "e2");
    Edge<String> e3 = graph.insert(v2, v1, "e3");
    List<Edge<String>> edges = new LinkedList<>();
    edges.add(e);
    edges.add(e1);
    edges.add(e2);
    edges.add(e3);
    assertEquals(edges, graph.edges());
  }

  @Test
  @DisplayName("Iterable outgoing() test")
  public void iterableOutgoingTest() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v, v1, "e");
    Edge<String> e1 = graph.insert(v, v2, "e1");
    Edge<String> e2 = graph.insert(v1, v2, "e2");
    Edge<String> e3 = graph.insert(v2, v1, "e3");
    Edge<String> e4 = graph.insert(v1, v, "e4");
    Edge<String> e5 = graph.insert(v2, v, "e5");
    List<Edge<String>> outgoingV = new LinkedList<>();
    outgoingV.add(e);
    outgoingV.add(e1);
    assertEquals(outgoingV, graph.outgoing(v));
    List<Edge<String>> outgoingV1 = new LinkedList<>();
    outgoingV1.add(e2);
    outgoingV1.add(e4);
    assertEquals(outgoingV1, graph.outgoing(v1));
    List<Edge<String>> outgoingV2 = new LinkedList<>();
    outgoingV2.add(e3);
    outgoingV2.add(e5);
    assertEquals(outgoingV2, graph.outgoing(v2));
  }

  @Test
  @DisplayName("Iterable incoming() test")
  public void iterableIncomingTest() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v, v1, "e");
    Edge<String> e1 = graph.insert(v, v2, "e1");
    Edge<String> e2 = graph.insert(v1, v2, "e2");
    Edge<String> e3 = graph.insert(v2, v1, "e3");
    Edge<String> e4 = graph.insert(v1, v, "e4");
    Edge<String> e5 = graph.insert(v2, v, "e5");
    List<Edge<String>> incomingV = new LinkedList<>();
    incomingV.add(e4);
    incomingV.add(e5);
    assertEquals(incomingV, graph.incoming(v));
    List<Edge<String>> incomingV1 = new LinkedList<>();
    incomingV1.add(e);
    incomingV1.add(e3);
    assertEquals(incomingV1, graph.incoming(v1));
    List<Edge<String>> incomingV2 = new LinkedList<>();
    incomingV2.add(e1);
    incomingV2.add(e2);
    assertEquals(incomingV2, graph.incoming(v2));
  }

  @Test
  @DisplayName("from() and to() function correctly")
  public void fromAndToTest() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v, v1, "e");
    Edge<String> e1 = graph.insert(v, v2, "e1");
    Edge<String> e2 = graph.insert(v1, v2, "e2");
    Edge<String> e3 = graph.insert(v2, v1, "e3");
    assertEquals(v, graph.from(e));
    assertEquals(v, graph.from(e1));
    assertEquals(v1, graph.from(e2));
    assertEquals(v2, graph.from(e3));
    assertEquals(v1, graph.to(e));
    assertEquals(v2, graph.to(e1));
    assertEquals(v2, graph.to(e2));
    assertEquals(v1, graph.to(e3));
  }

  @Test
  @DisplayName("clearLabels() functions correctly with multiple labels")
  public void clearLabelTest() {
    Vertex<String> v = graph.insert("v");
    graph.label(v, "1");
    Vertex<String> v1 = graph.insert("v1");
    graph.label(v1, "7");
    Vertex<String> v2 = graph.insert("v2");
    graph.label(v2, "3");
    Edge<String> e = graph.insert(v, v1, "e");
    graph.label(e, "1");
    Edge<String> e1 = graph.insert(v, v2, "e1");
    graph.label(e1, "3");
    Edge<String> e2 = graph.insert(v1, v2, "e2");
    graph.label(e2, "5");
    assertEquals("1", graph.label(v));
    assertEquals("7", graph.label(v1));
    assertEquals("3", graph.label(v2));
    assertEquals("1", graph.label(e));
    assertEquals("3", graph.label(e1));
    assertEquals("5", graph.label(e2));
    graph.clearLabels();
    assertEquals(null, graph.label(v));
    assertEquals(null, graph.label(v1));
    assertEquals(null, graph.label(v2));
    assertEquals(null, graph.label(e));
    assertEquals(null, graph.label(e1));
    assertEquals(null, graph.label(e2));
  }
}
