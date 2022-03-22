package starter;

import java.util.Iterator;

/**
 * A minimal (regular) BST.
 */
public class BinarySearchTree implements Iterable<Character> {
  private Node root;

  /**
   * Construct a Binary Search Tree from the given data values.
   *
   * @param data an array of characters to be inserted in this BST.
   *             Pre: data != null && data.length > 0
   */
  public BinarySearchTree(char[] data) {
    for (char letter : data) {
      root = insert(root, letter);
    }
  }

  // Helper method: recursive insert
  private Node insert(Node node, char data) {
    if (node == null) {
      return new Node(data);
    } else if (node.data > data) {
      node.left = insert(node.left, data);
    } else if (node.data < data) {
      node.right = insert(node.right, data);
    }
    node.size = getSizeOfSubTree(node) + 1;
    return node;
  }

  /**
   * Find the k^th smallest element stored in this BST.
   *
   * @param k the position of the target element
   *          in the in-order traversal of elements in this BST.
   *          Pre: 1 <= k <= root.size
   * @return the k^th smallest element stored in this BST.
   */
  public char findByPlace(int k) {
    Node target = findByPlaceHelper(k, root);
    return target.data;
  }

  //Helper method of findByPlace, return the node at target place
  private Node findByPlaceHelper(int k, Node curr) {
    int sizeOfLeft = getSizeOfSubTree(curr.left);
    //If target is the current node
    if (sizeOfLeft == k - 1) {
      return curr;
    } else if (sizeOfLeft >= k) {
      //If target is in the left subtree
      return findByPlaceHelper(k, curr.left);
    } else {
      //If target is in the right subtree
      return findByPlaceHelper(k - sizeOfLeft - 1, curr.right);
    }
  }

  //Return the size of the subtree rooted at current node
  private int getSizeOfSubTree(Node curr) {
    if (curr == null) {
      return 0;
    }
    return getSizeOfSubTree(curr.left) + getSizeOfSubTree(curr.right) + 1;
  }

  /**
   * Returns the position of key (one-indexed) in the linear sorted list
   * (in-order traversal) of elements of this BST.
   *
   * @param key a character stored in this BST.
   *            Pre: key is in this BST.
   * @return an integer between 1 and size of this BST.
   */
  public int findPlace(char key) {
    return findPlaceHelper(key, root);
  }

  private int findPlaceHelper(char key, Node curr) {
    if (key < curr.data) {
      return findPlaceHelper(key, curr.left);
    } else if (key > curr.data) {
      int addSize = getSizeOfSubTree(curr.left) + 1;
      return addSize + findPlaceHelper(key, curr.right);
    }
    return getSizeOfSubTree(curr.left) + 1;
  }

  @Override
  public Iterator<Character> iterator() {
    return new MysteryIterator();
  }

  private class MysteryIterator implements Iterator<Character> {
    private Node curr;

    MysteryIterator() {
      curr = root;
    }

    @Override
    public boolean hasNext() {
      return curr != null;
    }

    @Override
    public Character next() {
      while (true) {
        if (curr.left == null) {
          break;
        } else {
          Node predecessor = getPredecessor(curr);
          if (predecessor.right == null) {
            /* Augment the tree: make curr the right child of its predecessor */
            predecessor.right = curr;
            curr = curr.left;
          } else { /* Undo augmentation to restore original tree */
            predecessor.right = null;
            break;
          }
        }
      }

      Character data = curr.data;
      curr = curr.right;
      return data;
    }

    // Pre: node != null
    private Node getPredecessor(Node node) {
      Node predecessor = node.left;
      while (predecessor.right != null && predecessor.right != node) {
        predecessor = predecessor.right;
      }
      return predecessor;
    }
  }

  private static class Node {
    char data;
    Node left;
    Node right;
    int size;

    Node(char data) {
      this.data = data;
    }
  }
}
