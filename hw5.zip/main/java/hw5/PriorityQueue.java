package hw5;

import exceptions.EmptyException;

/**
 * Priority Queue of ordered values.
 *
 * <p>Any implementation of `PriorityQueue` *must* provide two constructors:
 * a default constructor (with no argument) and a non-default one which allows
 * a Comparator to be provided to _overwrite_ the natural ordering of the
 * element types.</p>
 *
 * @param <T> Element type.
 */
public interface PriorityQueue<T extends Comparable<T>> {
  /**
   * Insert a value.
   *
   * @param t Value to insert.
   */
  void insert(T t);

  /**
   * Remove best value.
   *
   * @throws EmptyException If queue is empty.
   */
  void remove() throws EmptyException;

  /**
   * Return best value.
   *
   * <p>The best value is the "largest" (the one with the highest priority)
   * in the queue as determined by the queue's comparator.</p>
   *
   * @return best value in the queue.
   * @throws EmptyException If queue is empty.
   */
  T best() throws EmptyException;

  /**
   * Check if no elements present.
   *
   * @return True if queue is empty, false otherwise.
   */
  boolean empty();
}
