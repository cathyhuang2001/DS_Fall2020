package hw7.hashing;

import hw7.Map;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OpenAddressingHashMap<K, V> implements Map<K, V> {
  private Node<K, V>[] map;
  private int capacity;
  private int size;
  private int[] primes = {23, 47, 97, 197, 797, 1597, 3203, 6421, 12853, 51437,
                          205759, 411527, 823117, 1646237,3292489, 6584983,
                          13169977};

  private class Node<K, V> {
    K key;
    V value;
    boolean tombstone;

    private Node(K key, V value) {
      this.key = key;
      this.value = value;
      this.tombstone = false;
    }
  }

  /**
   * Instantiate LinearProbingHashMap.
   */
  public OpenAddressingHashMap() {
    capacity = 0;
    map = new Node[primes[capacity]];
    size = 0;
  }

  private int hash1(K key) {
    int index = key.hashCode() % primes[capacity];
    if (index < 0) {
      index = index + primes[capacity];
    }
    return index;
  }

  private void resize() {
    Node<K, V>[] old = map;
    capacity = capacity + 1;
    map = new Node[primes[capacity]];
    size = 0;
    for (int i = 0; i < primes[capacity - 1]; i++) {
      if (old[i] != null && !old[i].tombstone) {
        insert(old[i].key, old[i].value);
      }
    }
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    // TODO Implement Me!
    if (k == null || has(k)) {
      throw new IllegalArgumentException();
    }
    if ((1.0 * size) / primes[capacity] >= 0.5) {
      resize();
    }
    int index = hash1(k);
    while (map[index] != null && !map[index].tombstone) {
      index = (index + 1) % primes[capacity];
    }
    map[index] = new Node<>(k, v);
    size++;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = hash1(k);
    while (map[index] != null) {
      if ((map[index].key).equals(k) && !map[index].tombstone) {
        break;
      }
      index = (index + 1) % primes[capacity];
    }
    V val = map[index].value;
    map[index].tombstone = true;
    size--;
    return val;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    // TODO Implement Me!
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = hash1(k);
    while (map[index] != null) {
      if ((map[index].key).equals(k) && !map[index].tombstone) {
        break;
      }
      index = (index + 1) % primes[capacity];
    }
    map[index].value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = hash1(k);
    while (map[index] != null) {
      if ((map[index].key).equals(k) && !map[index].tombstone) {
        break;
      }
      index = (index + 1) % primes[capacity];
    }
    return map[index].value;
  }

  @Override
  public boolean has(K k) {
    // TODO Implement Me!
    if (k == null) {
      return false;
    }
    int index = hash1(k);
    while (map[index] != null) {
      if ((map[index].key).equals(k) && !map[index].tombstone) {
        return true;
      }
      index = (index + 1) % primes[capacity];
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
    return new OpenIterator();
  }

  private class OpenIterator implements Iterator<K> {
    private int index;
    private int count;
    private int constSize = size;
    private Node<K, V> curr = map[index];

    @Override
    public boolean hasNext() {
      return count < constSize;
    }

    @Override
    public K next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      K currkey;
      if (curr != null && !curr.tombstone) {
        currkey = curr.key;
      } else {
        while (map[index] == null || map[index].tombstone) {
          index = index + 1;
        }
        curr = map[index];
        currkey = curr.key;
      }
      index++;
      curr = map[index];
      count++;
      return currkey;
    }
  }
}
