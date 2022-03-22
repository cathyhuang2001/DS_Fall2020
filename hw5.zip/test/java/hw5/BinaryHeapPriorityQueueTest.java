package hw5;

import java.util.Comparator;

/**
 * Instantiate the BinaryHeapPriorityQueue to test.
 */
public class BinaryHeapPriorityQueueTest extends PriorityQueueTest {
  @Override
  protected PriorityQueue<Integer> createUnit() {
    return new BinaryHeapPriorityQueue<>();
  }

  @Override
  protected PriorityQueue<Integer> createUnit(Comparator<Integer> comp) {
    return new BinaryHeapPriorityQueue<>(comp);
  }
}
