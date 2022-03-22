package hw7.hashing;

import hw7.Map;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ChainingHashMap<K, V> implements Map<K, V> {
  private Node<K, V>[] bucket;
  private int size;
  private int capacity;
  private int[] primes = {23, 47, 97, 197, 797, 1597, 3203, 6421, 12853,
                          51437, 205759, 411527, 823117, 1646237,
                          3292489, 6584983, 13169977};

  /**
   * Instantiate ChainingHashMap.
   */
  public ChainingHashMap() {
    capacity = 0;
    bucket = new Node[primes[capacity]];
    size = 0;
  }

  private class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;

    private Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  private int hash(K key) {
    int index = key.hashCode() % primes[capacity];
    if (index < 0) {
      index = index + primes[capacity];
    }
    return index;
  }

  private void resize() {
    Node[] old = bucket;
    capacity = capacity + 1;
    bucket = new Node[primes[capacity]];
    size = 0;
    for (int i = 0; i < primes[capacity - 1]; i++) {
      if (old[i] != null) {
        Node<K, V> curr = old[i];
        while (curr != null) {
          insert(curr.key, curr.value);
          curr = curr.next;
        }
      }
    }
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    // TODO Implement Me!
    if (k == null || has(k)) {
      throw new IllegalArgumentException();
    }
    if ((1.0 * size) / primes[capacity] >= 0.75) {
      resize();
    }
    int index = hash(k);
    Node<K, V> head = bucket[index];
    Node<K, V> target = new Node<K, V>(k, v);
    if (head != null) {
      target.next = head;
    }
    bucket[index] = target;
    size++;
  }

  private V remove(K k, int index) {
    Node<K, V> head = bucket[index];
    V val = null;
    if (head.key.equals(k)) {
      val = head.value;
      bucket[index] = head.next;
      size--;
      return val;
    }
    while (head.next != null) {
      if (head.key.equals(k)) {
        val = head.value;
        head.next = head.next.next;
        break;
      }
      head = head.next;
    }
    size--;
    return val;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = hash(k);
    return remove(k, index);
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    // TODO Implement Me!
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = hash(k);
    Node<K, V> head = bucket[index];
    while (head != null) {
      if (head.key.equals(k)) {
        head.value = v;
        return;
      }
      head = head.next;
    }
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = hash(k);
    Node<K, V> head = bucket[index];
    while (head != null) {
      if (head.key.equals(k)) {
        return head.value;
      }
      head = head.next;
    }
    return null;
  }

  @Override
  public boolean has(K k) {
    // TODO Implement Me!
    if (k == null) {
      return false;
    }
    int index = hash(k);
    Node<K, V> head = bucket[index];
    while (head != null) {
      if (head.key.equals(k)) {
        return true;
      }
      head = head.next;
    }
    return false;
  }

  @Override
  public int size() {
    // TODO Implement Me!
    return size;
  }

  @Override
  public Iterator<K> iterator() {
    // TODO Implement Me!
    return new ChainingIterator();
  }

  private class ChainingIterator implements Iterator<K> {
    private int index;
    private int constSize = size;
    private int count;
    private Node<K, V> curr = bucket[index];

    @Override
    public boolean hasNext() {
      return count < constSize;
    }

    @Override
    public K next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      if (curr != null) {
        K currKey = curr.key;
        curr = curr.next;
        count++;
        return currKey;
      }
      index++;
      while (index < bucket.length && bucket[index] == null) {
        index = index + 1;
      }
      curr = bucket[index];
      K currKey = curr.key;
      curr = curr.next;
      count++;
      return currKey;
    }
  }

}
