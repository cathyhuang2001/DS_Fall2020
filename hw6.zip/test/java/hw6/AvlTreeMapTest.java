package hw6;

import hw6.bst.AvlTreeMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to AVL Tree.
 */
@SuppressWarnings("All")
public class AvlTreeMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new AvlTreeMap<>();
  }

  @Test
  public void insertLeftRotation() {
    map.insert("1", "a");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a
     */

    map.insert("2", "b");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a,
        null 2:b
     */

    map.insert("3", "c"); // it must do a right rotation here!
    // System.out.println(avl.toString());
    // must print
    /*
        2:b,
        1:a 3:c
     */

    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // TODO Add more tests
  @Test
  public void sizeTest() {
    assertEquals(0, map.size());
    map.insert("3", "a");
    assertEquals(1, map.size());
  }

  @Test
  public void insertRightRotation() {
    map.insert("3", "a");
    map.insert("2", "b");
    map.insert("1", "c");
    String[] expected = new String[]{
            "2:b",
            "1:c 3:a"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeLeaf() {
    map.insert("3", "a");
    map.insert("4", "e");
    map.insert("2", "b");
    map.insert("5", "b");
    map.insert("1", "c");
    assertEquals("c", map.remove("1"));
    String[] expected1 = new String[]{
            "3:a",
            "2:b 4:e",
            "null null null 5:b"
    };
    assertEquals((String.join("\n", expected1) + "\n"), map.toString());
    assertEquals("b", map.remove("5"));
    String[] expected2 = new String[]{
            "3:a",
            "2:b 4:e"
    };
    assertEquals((String.join("\n", expected2) + "\n"), map.toString());
  }

  @Test
  public void removeNodeWithOneChild() {
    map.insert("1", "a");
    map.insert("7", "e");
    map.insert("2", "b");
    map.insert("5", "k");
    map.insert("3", "c");
    map.insert("4", "l");
    map.insert("9", "d");
    String[] expected1 = new String[]{
            "3:c",
            "2:b 5:k",
            "1:a null 4:l 7:e",
            "null null null null null null null 9:d"
    };
    assertEquals((String.join("\n", expected1) + "\n"), map.toString());
    assertEquals("e", map.remove("7"));
    String[] expected2 = new String[]{
            "3:c",
            "2:b 5:k",
            "1:a null 4:l 9:d"
    };
    assertEquals((String.join("\n", expected2) + "\n"), map.toString());
  }

  @Test
  public void removeMultipleNodes() {
    map.insert("1", "a");
    map.insert("7", "e");
    map.insert("2", "b");
    map.insert("5", "k");
    map.insert("3", "c");
    map.insert("4", "l");
    map.insert("9", "d");
    map.remove("3");
    String[] expected1 = new String[]{
            "5:k",
            "2:b 7:e",
            "1:a 4:l null 9:d"
    };
    assertEquals((String.join("\n", expected1) + "\n"), map.toString());
    assertEquals("k", map.remove("5"));
    String[] expected2 = new String[]{
            "4:l",
            "2:b 7:e",
            "1:a null null 9:d"
    };
    assertEquals((String.join("\n", expected2) + "\n"), map.toString());
    assertEquals("e", map.remove("7"));
    assertEquals("d", map.remove("9"));
    String[] expected3 = new String[]{
            "2:b",
            "1:a 4:l"
    };
    assertEquals((String.join("\n", expected3) + "\n"), map.toString());
  }

  @Test
  public void changeValues() {
    map.insert("1", "a");
    map.insert("2", "b");
    map.insert("5", "k");
    map.insert("3", "c");
    map.insert("7", "e");
    map.put("3", "b");
    assertEquals("b", map.get("3"));
    map.put("2", "k");
    assertEquals("k", map.get("2"));
    String[] expected = new String[]{
            "2:k",
            "1:a 5:k",
            "null null 3:b 7:e"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
    assertEquals("k", map.remove("2"));
    assertEquals("b", map.remove("3"));
  }
}
