package hw5;

import java.util.Comparator;

/**
 * Instantiate the SortedArrayPriorityQueue to test.
 */
public class SortedArrayPriorityQueueTest extends PriorityQueueTest {
  @Override
  protected PriorityQueue<Integer> createUnit() {
    return new SortedArrayPriorityQueue<>();
  }

  @Override
  protected PriorityQueue<Integer> createUnit(Comparator<Integer> comp) {
    return new SortedArrayPriorityQueue<>(comp);
  }
}
