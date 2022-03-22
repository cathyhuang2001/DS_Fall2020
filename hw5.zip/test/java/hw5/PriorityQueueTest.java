package hw5;

import exceptions.EmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing implementations of the PriorityQueue interface.
 */
public abstract class PriorityQueueTest {
  private PriorityQueue<Integer> unit;

  protected abstract PriorityQueue<Integer> createUnit();

  protected abstract PriorityQueue<Integer> createUnit(Comparator<Integer> comp);

  @BeforeEach
  public void setupTests() {
    unit = this.createUnit();
  }

  @Test
  @DisplayName("PQ empty after construction")
  public void newQueueEmpty() {
    assertTrue(unit.empty());
  }

  @Test
  @DisplayName("PQ not empty after inserting")
  public void queueEmpty() {
    unit.insert(1);
    assertFalse(unit.empty());
  }

  @Test
  @DisplayName("Basic PQ operations")
  public void queueTest1() {
    unit.insert(1);
    unit.insert(10);
    unit.insert(25);
    unit.insert(3);
    unit.insert(17);
    assertFalse(unit.empty());
    assertEquals(25, unit.best());
    unit.remove();
    assertEquals(17, unit.best());
    unit.remove();
    assertFalse(unit.empty());
    assertEquals(10, unit.best());
    unit.insert(10);
    assertEquals(10, unit.best());
    unit.insert(35);
    unit.insert(27);
    assertEquals(35, unit.best());
    unit.remove();
    assertEquals(27, unit.best());
  }

  @Test
  @DisplayName("New PQ throws exception when deleting")
  public void emptyTest1() {
    try {
      unit.remove();
      fail("EmptyException not thrown when removing from a new PQ.");
    } catch (EmptyException ex) {
      return;
    }
  }

  @Test
  @DisplayName("New PQ throws exception when getting best")
  public void emptyTest2() {
    try {
      unit.best();
      fail("EmptyException not thrown when getting best from a new PQ.");
    } catch (EmptyException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Empty PQ throws exception when removing")
  public void emptyTest3() {
    try {
      unit.insert(2);
      unit.insert(7);
      unit.remove();
      unit.remove();
      unit.remove();
      fail("EmptyException not thrown when removing from an empty PQ.");
    } catch (EmptyException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Empty PQ throws exception when getting best")
  public void emptyTest4() {
    try {
      unit.insert(2);
      unit.remove();
      unit.best();
      fail("EmptyException not thrown when getting best from an empty PQ.");
    } catch (EmptyException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Basic PQ operations with second constructor")
  public void queueTest2() {
    unit = this.createUnit(Collections.reverseOrder());
    unit.insert(1);
    unit.insert(10);
    unit.insert(25);
    unit.insert(3);
    unit.insert(17);
    assertFalse(unit.empty());
    assertEquals(1, unit.best());
    unit.remove();
    assertEquals(3, unit.best());
    unit.remove();
    assertFalse(unit.empty());
    assertEquals(10, unit.best());
    unit.insert(30);
    assertEquals(10, unit.best());
    unit.insert(5);
    unit.insert(27);
    assertEquals(5, unit.best());
  }

  @Test
  @DisplayName("New PQ throws exception when deleting with second constructor")
  public void emptyTest5() {
    try {
      unit = this.createUnit(Collections.reverseOrder());
      unit.remove();
      fail("EmptyException not thrown when removing from a new PQ.");
    } catch (EmptyException ex) {
      return;
    }
  }

  @Test
  @DisplayName("New PQ throws exception when getting best with second constructor")
  public void emptyTest6() {
    try {
      unit = this.createUnit(Collections.reverseOrder());
      unit.best();
      fail("EmptyException not thrown when getting best from a new PQ.");
    } catch (EmptyException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Empty PQ throws exception when removing with second constructor")
  public void emptyTest7() {
    try {
      unit = this.createUnit(Collections.reverseOrder());
      unit.insert(2);
      unit.insert(7);
      unit.remove();
      unit.remove();
      unit.remove();
      fail("EmptyException not thrown when removing from an empty PQ.");
    } catch (EmptyException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Empty PQ throws exception when getting best with second constructor")
  public void emptyTest8() {
    try {
      unit = this.createUnit(Collections.reverseOrder());
      unit.insert(2);
      unit.remove();
      unit.best();
      fail("EmptyException not thrown when getting best from an empty PQ.");
    } catch (EmptyException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Last is max test fail")
  public void removeLastTest() {
    unit.insert(17);
    unit.insert(37);
    assertEquals(37, unit.best());
    unit.remove();
    assertEquals(17, unit.best());
  }
}
