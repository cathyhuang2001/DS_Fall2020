package starter;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class only tests BinarySearchTree.findPlace
 */
class BinarySearchTreeTest {

  private final char[] inorder = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

  @Test
  void findPlaceWorksForRightSkewed() {
    char[] data = {'A', 'B', 'C', 'D', 'E'};
    BinarySearchTree bst = new BinarySearchTree(data);
    for (int k = 1; k <= data.length; k++) {
      assertEquals(k, bst.findPlace(inorder[k - 1]));
    }
  }

  @Test
  void findPlaceWorksForLeftSkewed() {
    char[] data = {'E', 'D', 'C', 'B', 'A'};
    BinarySearchTree bst = new BinarySearchTree(data);
    for (int k = 1; k <= data.length; k++) {
      assertEquals(k, bst.findPlace(inorder[k - 1]));
    }
  }

  @Test
  void findPlaceWorksForRightHeavyBST() {
    char[] data = {'B', 'A', 'D', 'C', 'E'};
    BinarySearchTree bst = new BinarySearchTree(data);
    for (int k = 1; k <= data.length; k++) {
      assertEquals(k, bst.findPlace(inorder[k - 1]));
    }
  }

  @Test
  void findPlaceWorksForLeftHeavyBST() {
    char[] data = {'B', 'A', 'D', 'C', 'E'};
    BinarySearchTree bst = new BinarySearchTree(data);
    for (int k = 1; k <= data.length; k++) {
      assertEquals(k, bst.findPlace(inorder[k - 1]));
    }
  }

  @Test
  void findPlaceWorksForPerfectBST() {
    char[] data = {'D', 'B', 'F', 'A', 'C', 'E', 'G'};
    BinarySearchTree bst = new BinarySearchTree(data);
    for (int k = 1; k <= data.length; k++) {
      assertEquals(k, bst.findPlace(inorder[k - 1]));
    }
  }

  @Test
  void findPlaceWorksForRandomBST() {
    List<Character> list = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G');
    Collections.shuffle(list);
    char[] data = new char[list.size()];
    for (int i = 0; i < data.length; i++) {
      data[i] = list.get(i);
    }
    BinarySearchTree bst = new BinarySearchTree(data);
    for (int k = 1; k <= data.length; k++) {
      assertEquals(k, bst.findPlace(inorder[k - 1]));
    }
  }
}