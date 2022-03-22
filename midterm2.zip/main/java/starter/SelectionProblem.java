package starter;

public class SelectionProblem {

  /**
   * Find the k^th smallest element in the given data array.
   * @param data an array of characters.
   *             Pre: data != null && data.length > 0
   * @param k the position of the target element
   *          in the linear sorted list of elements in data.
   *          Pre: 1 <= k <= data.length
   * @return the k^th smallest element in the given data array.
   */
  public static int solve(char[] data, int k) {
    BinarySearchTree bst = new BinarySearchTree(data);
    return bst.findByPlace(k);
  }
}
