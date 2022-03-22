package starter;

public class MysteryIteratorDemo {

  /**
   * Demo starts here.
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    BinarySearchTree bst = new BinarySearchTree("BALTIMORE".toCharArray());
    for (char value: bst) {
      System.out.print(value + " ");
    }
  }
}
