package hw8;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * An implementation of Graph ADT using incidence lists
 * for sparse graphs where most nodes aren't connected.
 *
 * @param <V> Vertex element type.
 * @param <E> Edge element type.
 */
public class SparseGraph<V, E> implements Graph<V, E> {

  // TODO You may need to add fields/constructor here!
  private List<VertexNode<V>> vertices;
  private List<EdgeNode<E>> edges;

  /**
   * Constructor of SparseGraph.
   */
  public SparseGraph() {
    vertices = new LinkedList<>();
    edges = new LinkedList<>();
  }

  // Converts the vertex back to a VertexNode to use internally
  private VertexNode<V> convert(Vertex<V> v) throws PositionException {
    try {
      VertexNode<V> gv = (VertexNode<V>) v;
      if (gv.owner != this) {
        throw new PositionException();
      }
      return gv;
    } catch (NullPointerException | ClassCastException ex) {
      throw new PositionException();
    }
  }

  // Converts and edge back to a EdgeNode to use internally
  private EdgeNode<E> convert(Edge<E> e) throws PositionException {
    try {
      EdgeNode<E> ge = (EdgeNode<E>) e;
      if (ge.owner != this) {
        throw new PositionException();
      }
      return ge;
    } catch (NullPointerException | ClassCastException ex) {
      throw new PositionException();
    }
  }

  private boolean includeV(VertexNode<V> v) {
    for (VertexNode<V> curr : vertices) {
      if (curr.data.equals(v.data)) {
        return true;
      }
    }
    return false;
  }

  private boolean includeE(EdgeNode<E> e) {
    for (EdgeNode<E> curr : edges) {
      if (curr.from.equals(e.from) && curr.to.equals(e.to)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Vertex<V> insert(V v) throws InsertionException {
    // TODO Implement me!
    VertexNode<V> node = new VertexNode<>(v);
    if (v == null) {
      throw new InsertionException();
    }
    if (includeV(node)) {
      throw new InsertionException();
    }
    vertices.add(node);
    node.owner = this;
    return node;
  }

  @Override
  public Edge<E> insert(Vertex<V> from, Vertex<V> to, E e)
      throws PositionException, InsertionException {
    // TODO Implement me!
    VertexNode<V> fromV = convert(from);
    VertexNode<V> toV = convert(to);
    if (!includeV(fromV) || !includeV(toV)
            || fromV.data == null || toV.data == null) {
      throw new PositionException();
    }
    EdgeNode<E> edge = new EdgeNode<>(fromV, toV, e);
    if (fromV.data.equals(toV.data) || includeE(edge)) {
      throw new InsertionException();
    }
    edges.add(edge);
    fromV.outgoing.add(edge);
    toV.incoming.add(edge);
    edge.owner = this;
    return edge;
  }

  @Override
  public V remove(Vertex<V> v) throws PositionException, RemovalException {
    // TODO Implement me!
    VertexNode<V> node = convert(v);
    if (!includeV(node) || node.data == null) {
      throw new PositionException();
    }
    if (node.outgoing.size() != 0 || node.incoming.size() != 0) {
      throw new RemovalException();
    }
    V val = node.data;
    vertices.remove(node);
    return val;
  }

  @Override
  public E remove(Edge<E> e) throws PositionException {
    // TODO Implement me!
    EdgeNode<E> edge = convert(e);
    if (!includeE(edge) || edge.data == null) {
      throw new PositionException();
    }
    VertexNode<V> fromV = edge.from;
    VertexNode<V> toV = edge.to;
    fromV.outgoing.remove(edge);
    toV.incoming.remove(edge);
    E val = edge.data;
    edges.remove(edge);
    return val;
  }

  @Override
  public Iterable<Vertex<V>> vertices() {
    // TODO Implement me!
    List<Vertex<V>> verticesCopy = Collections.unmodifiableList(vertices);
    return verticesCopy;
  }

  @Override
  public Iterable<Edge<E>> edges() {
    // TODO Implement me!
    List<Edge<E>> edgesCopy = Collections.unmodifiableList(edges);
    return edgesCopy;
  }

  @Override
  public Iterable<Edge<E>> outgoing(Vertex<V> v) throws PositionException {
    // TODO Implement me!
    VertexNode<V> node = convert(v);
    if (!vertices.contains(node)) {
      throw new PositionException();
    }
    List<Edge<E>> outCopy = Collections.unmodifiableList(node.outgoing);
    return outCopy;
  }

  @Override
  public Iterable<Edge<E>> incoming(Vertex<V> v) throws PositionException {
    // TODO Implement me!
    VertexNode<V> node = convert(v);
    if (!vertices.contains(node)) {
      throw new PositionException();
    }
    List<Edge<E>> inCopy = Collections.unmodifiableList(node.incoming);
    return inCopy;
  }

  @Override
  public Vertex<V> from(Edge<E> e) throws PositionException {
    // TODO Implement me!
    EdgeNode<E> edge = convert(e);
    if (!includeE(edge)) {
      throw new PositionException();
    }
    return edge.from;
  }

  @Override
  public Vertex<V> to(Edge<E> e) throws PositionException {
    // TODO Implement me!
    EdgeNode<E> edge = convert(e);
    if (!includeE(edge)) {
      throw new PositionException();
    }
    return edge.to;
  }

  @Override
  public void label(Vertex<V> v, Object l) throws PositionException {
    // TODO Implement me!
    VertexNode<V> node = convert(v);
    if (!includeV(node)) {
      throw new PositionException();
    }
    node.label = l;
  }

  @Override
  public void label(Edge<E> e, Object l) throws PositionException {
    // TODO Implement me!
    EdgeNode<E> edge = convert(e);
    if (!includeE(edge)) {
      throw new PositionException();
    }
    edge.label = l;
  }

  @Override
  public Object label(Vertex<V> v) throws PositionException {
    // TODO Implement me!
    VertexNode<V> node = convert(v);
    if (!includeV(node)) {
      throw new PositionException();
    }
    return node.label;
  }

  @Override
  public Object label(Edge<E> e) throws PositionException {
    // TODO Implement me!
    EdgeNode<E> edge = convert(e);
    if (!includeE(edge)) {
      throw new PositionException();
    }
    return edge.label;
  }

  @Override
  public void clearLabels() {
    // TODO Implement me!
    for (VertexNode<V> curr : vertices) {
      curr.label = null;
    }
    for (EdgeNode<E> currE : edges) {
      currE.label = null;
    }
  }

  @Override
  public String toString() {
    GraphPrinter<V, E> gp = new GraphPrinter<>(this);
    return gp.toString();
  }

  // Class for a vertex of type V
  private final class VertexNode<V> implements Vertex<V> {
    V data;
    Graph<V, E> owner;
    Object label;
    // TODO You may need to add fields/methods here!
    LinkedList<EdgeNode<E>> outgoing;
    LinkedList<EdgeNode<E>> incoming;

    VertexNode(V v) {
      this.data = v;
      this.label = null;
      this.outgoing = new LinkedList<>();
      this.incoming = new LinkedList<>();
    }

    @Override
    public V get() {
      return this.data;
    }
  }

  //Class for an edge of type E
  private final class EdgeNode<E> implements Edge<E> {
    E data;
    Graph<V, E> owner;
    VertexNode<V> from;
    VertexNode<V> to;
    Object label;
    // TODO You may need to add fields/methods here!

    // Constructor for a new edge
    EdgeNode(VertexNode<V> f, VertexNode<V> t, E e) {
      this.from = f;
      this.to = t;
      this.data = e;
      this.label = null;
    }

    @Override
    public E get() {
      return this.data;
    }
  }
}
