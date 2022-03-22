package starter;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectionProblemTest {

  private final char[] inorder = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

  @Test
  void solveForSortedData() {
    char[] data = {'A', 'B', 'C', 'D', 'E'};
    for (int k = 1; k <= data.length; k++) {
      assertEquals(inorder[k - 1], SelectionProblem.solve(data,k));
    }
  }

  @Test
  void solveForReverseSortedData() {
    char[] data = {'E', 'D', 'C', 'B', 'A'};
    for (int k = 1; k <= data.length; k++) {
      assertEquals(inorder[k - 1], SelectionProblem.solve(data,k));
    }
  }

  @Test
  void solveForUnorderedData_Case1() {
    char[] data = {'B', 'A', 'D', 'C', 'E'};
    for (int k = 1; k <= data.length; k++) {
      assertEquals(inorder[k - 1], SelectionProblem.solve(data,k));
    }
  }

  @Test
  void solveForUnorderedData_Case2() {
    char[] data = {'B', 'A', 'D', 'C', 'E'};
    for (int k = 1; k <= data.length; k++) {
      assertEquals(inorder[k - 1], SelectionProblem.solve(data,k));
    }
  }

  @Test
  void solveForUnorderedData_Case3() {
    char[] data = {'D', 'B', 'F', 'A', 'C', 'E', 'G'};
    for (int k = 1; k <= data.length; k++) {
      assertEquals(inorder[k - 1], SelectionProblem.solve(data,k));
    }
  }

  @Test
  void solveForRandomlyOrderedData() {
    List<Character> list = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G');
    Collections.shuffle(list);
    char[] data = new char[list.size()];
    for (int i = 0; i < data.length; i++) {
      data[i] = list.get(i);
    }
    for (int k = 1; k <= data.length; k++) {
      assertEquals(inorder[k - 1], SelectionProblem.solve(data,k));
    }
  }
}