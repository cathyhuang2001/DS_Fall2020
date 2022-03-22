package hw5;

import exceptions.EmptyException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Priority queue implemented as a binary heap.
 *
 * <p> Use the ranked array representation of a binary heap!
 * Keep the arithmetic simple by sticking a null into slot 0 of the
 * ArrayList; wasting one reference is an okay price to pay for nicer
 * code.</p>
 *
 * @param <T> Element type.
 */
public class BinaryHeapPriorityQueue<T extends Comparable<T>>
    implements PriorityQueue<T> {

  private Comparator<T> cmp;
  private List<T> data;

  /**
   * A binary heap using the "natural" ordering of T.
   */
  public BinaryHeapPriorityQueue() {
    this(new DefaultComparator<>());
  }

  /**
   * A binary heap using the given comparator for T.
   *
   * @param cmp Comparator to use.
   */
  public BinaryHeapPriorityQueue(Comparator<T> cmp) {
    this.cmp = cmp;
    data = new ArrayList<>();
    // ArrayList is a dynamic array (part of Java Collection Framework)
    // You are allowed to use it for this homework!
    // TODO: Complete me
    data.add(null);
  }

  /**
   * Swapping the data stored at two indices.
   * @param i First index of swap.
   * @param j Second index of swap.
   */
  public void swap(int i, int j) {
    T temp1 = data.get(i);
    T temp2 = data.get(j);
    data.set(j, temp1);
    data.set(i, temp2);
  }

  // Value in the slot i "less" than value in the slot j?
  private boolean less(int i, int j) {
    return cmp.compare(data.get(i), data.get(j)) < 0;
  }

  @Override
  public void insert(T t) {
    data.add(t);
    if (data.size() != 2) {
      int i = data.size() - 1;
      int parent = i / 2;
      while (less(parent, i)) {
        swap(i, parent);
        i = parent;
        if (i == 1) {
          break;
        } else {
          parent = i / 2;
        }
      }
    }
  }

  //Choose the larger of the children in case of swap.
  private int decideSwap(int leftChild, int rightChild) {
    int swap = leftChild;
    if (less(leftChild, rightChild)) {
      swap = rightChild;
    }
    return swap;
  }

  //Helper method of sink.
  private void sink2(int leftChild, int rightChild, int current) {
    //Operate if the parent has child
    while (leftChild < data.size()) {
      //If the parent has two children
      if (rightChild < data.size()) {
        //Choose the larger of the children
        int swap = decideSwap(leftChild, rightChild);
        //Swap if child is larger than parent.
        if (less(current, swap)) {
          swap(swap, current);
          current = swap;
          leftChild = 2 * current;
          rightChild = 2 * current + 1;
        } else {
          break;
        }
      } else {
        //If the parent has only one child
        if (less(current, leftChild)) {
          swap(leftChild, current);
        }
        break;
      }
    }
  }

  //Sink the new front node down.
  private void sink() {
    int current = 1;
    int last = data.size() - 1;
    //Swap the maximum to the last to remove.
    swap(current, last);
    T t = data.remove(last);
    int leftChild = 2 * current;
    int rightChild = 2 * current + 1;
    //Sink the new front back down.
    sink2(leftChild, rightChild, current);
  }

  @Override
  public void remove() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    sink();
  }

  @Override
  public T best() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    return data.get(1);
  }

  @Override
  public boolean empty() {
    return data.size() == 1;
  }

  // The default comparator uses the "natural" ordering.
  private static class DefaultComparator<T extends Comparable<T>>
      implements Comparator<T> {
    public int compare(T t1, T t2) {
      return t1.compareTo(t2);
    }
  }
}
