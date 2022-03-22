package hw6.bst;

import hw6.OrderedMap;
import java.util.Iterator;
import java.util.Stack;

/**
 * Map implemented as an AVL Tree.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class AvlTreeMap<K extends Comparable<K>, V>
    implements OrderedMap<K, V> {

  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;
  private int size;

  private int getHeight(Node<K, V> n) {
    if (n == null) {
      return -1;
    }
    return n.height;
  }

  private Node<K, V> rightRotation(Node<K, V> n) {
    Node<K, V> n2 = n.left;
    n.left = n2.right;
    n2.right = n;
    n.height = 1 + Math.max(getHeight(n.left), getHeight(n.right));
    n2.height = 1 + Math.max(getHeight(n2.left), getHeight(n2.right));
    return n2;
  }

  private Node<K, V> leftRotation(Node<K, V> n) {
    Node<K, V> n2 = n.right;
    n.right = n2.left;
    n2.left = n;
    n.height = 1 + Math.max(getHeight(n.left), getHeight(n.right));
    n2.height = 1 + Math.max(getHeight(n2.left), getHeight(n2.right));
    return n2;
  }

  private int balanceFactor(Node<K, V> n) {
    return getHeight(n.left) - getHeight(n.right);
  }

  private Node<K, V> balance(Node<K, V> n) {
    if (balanceFactor(n) < -1) {
      if (balanceFactor(n.right) > 0) {
        n.right = rightRotation(n.right);
      }
      n = leftRotation(n);
    } else if (balanceFactor(n) > 1) {
      if (balanceFactor(n.left) < 0) {
        n.left = leftRotation(n.left);
      }
      n = rightRotation(n);
    }
    return n;
  }

  private Node<K, V> insert(Node<K, V> n, K k, V v) {
    if (n == null) {
      return new Node<>(k, v);
    }
    int cmp = k.compareTo(n.key);
    if (cmp < 0) {
      n.left = insert(n.left, k, v);
    } else if (cmp > 0) {
      n.right = insert(n.right, k, v);
    } else {
      throw new IllegalArgumentException("duplicate key " + k);
    }
    n.height = 1 + Math.max(getHeight(n.left), getHeight(n.right));
    return balance(n);
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    // TODO Implement Me!
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }
    root = insert(root, k, v);
    size++;
  }

  private Node<K, V> max(Node<K, V> node) {
    Node<K, V> curr = node.left;
    while (curr.right != null) {
      curr = curr.right;
    }
    return curr;
  }

  private Node<K, V> remove(Node<K, V> node) {
    // Easy if the node has 0 or 1 child.
    if (node.right == null) {
      return node.left;
    } else if (node.left == null) {
      return node.right;
    }
    // If it has two children, find the predecessor (max in left subtree),
    Node<K, V> toReplaceWith = max(node);
    // then copy its data to the given node (value change),
    node.key = toReplaceWith.key;
    node.value = toReplaceWith.value;
    // then remove the predecessor node (structural change).
    node.left = remove(node.left, toReplaceWith);
    return balance(node);
  }

  private Node<K, V> remove(Node<K, V> subtreeRoot, Node<K, V> toRemove) {
    int cmp = subtreeRoot.key.compareTo(toRemove.key);
    if (cmp == 0) {
      return remove(subtreeRoot);
    } else if (cmp > 0) {
      subtreeRoot.left = remove(subtreeRoot.left, toRemove);
    } else {
      subtreeRoot.right = remove(subtreeRoot.right, toRemove);
    }
    int leftHeight = getHeight(subtreeRoot.left);
    int rightHeight = getHeight(subtreeRoot.right);
    int maxHeight = Math.max(leftHeight, rightHeight);
    subtreeRoot.height = 1 + maxHeight;
    return balance(subtreeRoot);
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }
    Node<K, V> node = findForSure(k);
    V value = node.value;
    root = remove(root, node);
    size--;
    return value;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    // TODO Implement Me!
    if (k == null || !has(k)) {
      throw new IllegalArgumentException("Inputted key to put() is invalid");
    }
    Node<K, V> n = findForSure(k);
    n.value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    if (k == null || !has(k)) {
      throw new IllegalArgumentException("Inputted key to get() is invalid");
    }
    return findForSure(k).value;
  }

  private Node<K, V> find(K k) {
    Node<K, V> curr = root;
    while (curr != null) {
      int cmp = k.compareTo(curr.key);
      if (cmp < 0) {
        curr = curr.left;
      } else if (cmp > 0) {
        curr = curr.right;
      } else {
        return curr;
      }
    }
    return null;
  }

  // Return node for given key, throw an exception if the key is not
  // in the tree.
  private Node<K, V> findForSure(K k) {
    Node<K, V> n = find(k);
    if (n == null) {
      throw new IllegalArgumentException("cannot find key " + k);
    }
    return n;
  }

  @Override
  public boolean has(K k) {
    // TODO Implement Me!
    if (k == null) {
      return false;
    }
    return find(k) != null;
  }

  @Override
  public int size() {
    // TODO Implement Me!
    return size;
  }

  @Override
  public Iterator<K> iterator() {
    // TODO Implement Me!
    return new InorderIterator();
  }

  private class InorderIterator implements Iterator<K> {
    private final Stack<Node<K, V>> stack;

    InorderIterator() {
      stack = new Stack<>();
      pushLeft(root);
    }

    private void pushLeft(Node<K, V> curr) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
    }

    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    @Override
    public K next() {
      Node<K, V> top = stack.pop();
      pushLeft(top.right);
      return top.key;
    }
  }

  /*** Do not change this function's name or modify its code. ***/
  @Override
  public String toString() {
    return BinaryTreePrinter.printBinaryTree(root);
  }

  /**
   * Feel free to add whatever you want to the Node class (e.g. new fields).
   * Just avoid changing any existing names, deleting any existing variables,
   * or modifying the overriding methods.
   *
   * <p>Inner node class, each holds a key (which is what we sort the
   * BST by) as well as a value. We don't need a parent pointer as
   * long as we use recursive insert/remove helpers.</p>
   **/
  private static class Node<K, V> implements BinaryTreeNode {
    Node<K, V> left;
    Node<K, V> right;
    K key;
    V value;
    int height;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null
      key = k;
      value = v;
      height = 0;
    }

    @Override
    public String toString() {
      return key + ":" + value;
    }

    @Override
    public BinaryTreeNode getLeftChild() {
      return left;
    }

    @Override
    public BinaryTreeNode getRightChild() {
      return right;
    }
  }

}
