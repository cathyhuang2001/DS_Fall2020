package hw6;

import hw6.bst.TreapMap;
import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to Treap.
 */
@SuppressWarnings("All")
public class TreapMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    Random randnum = new Random(0);
    return new TreapMap<>(randnum);
  }

  // TODO Add tests
  //  (think about how you might write tests while randomness is involved in TreapMap implementation!)

  @Test
  public void sizeTest() {
    assertEquals(0, map.size());
    map.insert("3", "a");
    assertEquals(1, map.size());
  }

  /*
  //See the sequence of prority with the seed 0.
  @Test
  public void randomTest() {
    Random randnum = new Random(0);
    for (int i = 0; i < 10; i++) {
      System.out.println(randnum.nextInt());
    }
  }
  */

  @Test
  public void insertTest() {
    map.insert("5", "a");
    map.insert("2", "c");
    map.insert("3", "b");
    map.insert("4", "d");
    String[] expected1 = new String[]{
            "4:d:-1690734402",
            "2:c:-723955400 5:a:-1155484576",
            "null 3:b:1033096058 null null"
    };
    assertEquals((String.join("\n", expected1) + "\n"), map.toString());
    map.insert("9", "l");
    String[] expected2 = new String[]{
            "4:d:-1690734402",
            "2:c:-723955400 9:l:-1557280266",
            "null 3:b:1033096058 5:a:-1155484576 null"
    };
    assertEquals((String.join("\n", expected2) + "\n"), map.toString());
    map.insert("1", "e");
    map.insert("7", "v");
    String[] expected3 = new String[]{
            "7:v:-1930858313",
            "4:d:-1690734402 9:l:-1557280266",
            "2:c:-723955400 5:a:-1155484576 null null",
            "1:e:1327362106 3:b:1033096058 null null null null null null"
    };
    assertEquals((String.join("\n", expected3) + "\n"), map.toString());
    assertEquals(7, map.size());
  }

  @Test
  public void removeLeaf() {
    map.insert("5", "a");
    map.insert("2", "c");
    map.insert("3", "b");
    map.insert("4", "d");
    map.insert("9", "l");
    map.insert("1", "e");
    map.insert("7", "v");
    assertEquals("e", map.remove("1"));
    String[] expected = new String[]{
            "7:v:-1930858313",
            "4:d:-1690734402 9:l:-1557280266",
            "2:c:-723955400 5:a:-1155484576 null null",
            "null 3:b:1033096058 null null null null null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
    assertEquals(6, map.size());
  }

  @Test
  public void removeNodeWithOneChild() {
    map.insert("5", "a");
    map.insert("2", "c");
    map.insert("3", "b");
    map.insert("4", "d");
    map.insert("9", "l");
    assertEquals("l", map.remove("9"));
    String[] expected = new String[]{
            "4:d:-1690734402",
            "2:c:-723955400 5:a:-1155484576",
            "null 3:b:1033096058 null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
    assertEquals(4, map.size());
  }

  @Test
  public void removeNodeWithTwoChildren() {
    map.insert("5", "a");
    map.insert("2", "c");
    map.insert("3", "b");
    map.insert("4", "d");
    map.insert("9", "l");
    map.insert("1", "e");
    map.insert("7", "v");
    map.remove("4");
    String[] expected1 = new String[]{
            "7:v:-1930858313",
            "5:a:-1155484576 9:l:-1557280266",
            "2:c:-723955400 null null null",
            "1:e:1327362106 3:b:1033096058 null null null null null null"
    };
    assertEquals((String.join("\n", expected1) + "\n"), map.toString());
    assertEquals(6, map.size());
    assertEquals("v", map.remove("7"));
    String[] expected2 = new String[]{
            "9:l:-1557280266",
            "5:a:-1155484576 null",
            "2:c:-723955400 null null null",
            "1:e:1327362106 3:b:1033096058 null null null null null null"
    };
    assertEquals((String.join("\n", expected2) + "\n"), map.toString());
  }

  @Test
  public void multipleChanges() {
    map.insert("1", "r");
    map.insert("3", "q");
    map.insert("2", "n");
    map.insert("7", "v");
    map.insert("8", "s");
    String[] expected1 = new String[]{
            "7:v:-1690734402",
            "1:r:-1155484576 8:s:-1557280266",
            "null 3:q:-723955400 null null",
            "null null 2:n:1033096058 null null null null null"
    };
    assertEquals((String.join("\n", expected1) + "\n"), map.toString());
    assertEquals("r", map.remove("1"));
    String[] expected2 = new String[]{
            "7:v:-1690734402",
            "3:q:-723955400 8:s:-1557280266",
            "2:n:1033096058 null null null"
    };
    assertEquals((String.join("\n", expected2) + "\n"), map.toString());
    assertEquals(4, map.size());
    map.insert("5", "k");
    map.insert("6", "s");
    String[] expected3 = new String[]{
            "6:s:-1930858313",
            "3:q:-723955400 7:v:-1690734402",
            "2:n:1033096058 5:k:1327362106 null 8:s:-1557280266"
    };
    assertEquals((String.join("\n", expected3) + "\n"), map.toString());
    assertEquals(6, map.size());
    assertEquals("v", map.remove("7"));
    assertEquals("q", map.remove("3"));
    String[] expected4 = new String[]{
            "6:s:-1930858313",
            "2:n:1033096058 8:s:-1557280266",
            "null 5:k:1327362106 null null"
    };
    assertEquals((String.join("\n", expected4) + "\n"), map.toString());
    assertEquals(4, map.size());
    map.insert("1", "l");
    map.insert("9", "b");
    map.insert("7", "k");
    String[] expected5 = new String[]{
            "6:s:-1930858313",
            "1:l:502539523 9:b:-1728529858",
            "null 2:n:1033096058 8:s:-1557280266 null",
            "null null null 5:k:1327362106 7:k:-938301587 null null null"
    };
    assertEquals((String.join("\n", expected5) + "\n"), map.toString());
  }

  @Test
  public void changeValues() {
    map.insert("1", "a");
    map.insert("2", "b");
    map.insert("5", "k");
    map.insert("3", "c");
    map.insert("7", "e");
    map.put("3", "b");
    assertEquals(map.get("3"), "b");
    map.put("2", "k");
    assertEquals(map.get("2"), "k");
    String[] expected = new String[]{
            "3:b:-1690734402",
            "1:a:-1155484576 7:e:-1557280266",
            "null 2:k:-723955400 5:k:1033096058 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
    assertEquals("b", map.remove("3"));
    assertEquals("k", map.remove("2"));
  }
}