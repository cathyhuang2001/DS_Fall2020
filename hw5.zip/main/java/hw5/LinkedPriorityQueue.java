package hw5;

import exceptions.EmptyException;
import java.util.Comparator;
import list.LinkedList;
import list.List;
import list.Position;

/**
 * Priority queue implemented using our (Positional) List.
 *
 * @param <T> Element type.
 */
public class LinkedPriorityQueue<T extends Comparable<T>>
    implements PriorityQueue<T> {

  private List<T> list;
  private Comparator<T> cmp;

  /**
   * An unordered List PQ using the "natural" ordering of T.
   */
  public LinkedPriorityQueue() {
    this(new DefaultComparator<>());
  }

  /**
   * An unordered List PQ using the given comparator for T.
   *
   * @param cmp Comparator to use.
   */
  public LinkedPriorityQueue(Comparator<T> cmp) {
    list = new LinkedList<>();
    this.cmp = cmp;
  }

  private boolean less(T i, T j) {
    return cmp.compare(i, j) < 0;
  }

  @Override
  public void insert(T t) {
    list.insertBack(t);
  }

  //Find the position that contains the maximum value.
  private Position<T> getMax() {
    Position<T> front = list.front();
    Position<T> max = front;
    Position<T> current = front;
    while (current != list.back()) {
      current = list.next(current);
      T t2 = current.get();
      if (!less(t2, max.get())) {
        max = current;
      }
    }
    return max;
  }

  @Override
  public void remove() throws EmptyException {
    if (list.empty()) {
      throw new EmptyException();
    }
    list.remove(getMax());
  }

  @Override
  public T best() throws EmptyException {
    if (list.empty()) {
      throw new EmptyException();
    }
    return getMax().get();
  }

  @Override
  public boolean empty() {
    return list.empty();
  }

  // The default comparator uses the "natural" ordering.
  private static class DefaultComparator<T extends Comparable<T>>
      implements Comparator<T> {
    public int compare(T t1, T t2) {
      return t1.compareTo(t2);
    }
  }
}
