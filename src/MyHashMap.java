import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size;
    private Node<K, V>[] buckets;

    public MyHashMap() {
        buckets = new Node[DEFAULT_CAPACITY];
    }

    class Node<K, V> {
        private int hashCode;
        private final K key;
        private V value;
        private Node<K, V> next;

        Node(K key, V value, int hashCode, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> that = (Node<?, ?>) o;
            return Objects.equals(key, that.key);
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    public int hash(K key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int indexForArray(int hashCode, int DEFAULT_CAPACITY) {
        int index = Math.abs(hashCode) % DEFAULT_CAPACITY;
        return index;
    }

    public void put(K key, V value) {
        Node<K, V> newElement = new Node<>(key, value, hash(key), null);
        int index = getIndex(newElement.getKey());

        if (buckets[index] == null) {
            buckets[index] = newElement;
            size++;
        } else {
            if (buckets[index].equals(newElement)) {
                buckets[index].setValue(newElement.getValue());
            } else {
                Node<K, V> element = buckets[index];
                while (element.next != null) {
                    if (element.next.getKey().equals(key)) {
                        element.next.setValue(newElement.getValue());
                        return;
                    }
                    element = element.next;
                }
                element.next = newElement;
                size++;
            }
        }
    }

    public V remove(K key) {
        if (size > 0) {
            if (key == null) {
                return removeForNullKey();
            }

            Node<K, V> first, n;
            K k;
            int i = getIndex(key), hash = hash(key);
            if ((first = buckets[i]) != null) {
                if ((k = (K) first.key) == key || k.equals(key) && hash == first.hashCode) {
                    buckets[i] = first.next;
                    size--;
                    return first.value == null ? null : (V) first.value;
                }

                if (first.next != null) {
                    for (n = first.next; n != null; n = n.next, first = first.next) {
                        if ((k = (K) n.key) == key || k.equals(key) && hash == n.hashCode) {
                            first.next = n.next;
                            size--;
                            return n.value == null ? null : (V) n.value;
                        }
                    }
                }
            }
        }

        return null;
    }

    private V removeForNullKey() {
        Node<K, V> n = buckets[0];
        V result;
        if (n != null) {
            result = n.value == null ? null : (V) n.value;

            if (n.next == null) {
                buckets[0] = null;
            } else {
                buckets[0] = new Node<K, V>(null, null, hash(null), n.next);
            }

            size--;
            return result;
        }

        return null;
    }

    protected int getIndex(K key) {
        if (key == null) {
            return 0;
        }

        int hash = hash(key);
        return ((buckets.length - 1) & hash);
    }

    public void clear() {
        buckets = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public V get(Object key) {
        int index = indexForArray(hash((K) key), DEFAULT_CAPACITY);
        Node<K, V> node = buckets[index];
        for (Node<K, V> n = node; n != null; n = n.next) {
            if ((key == null && null == n.getKey()) || (key != null && key.equals(n.getKey()))) {
                return (V) n.value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "size=" + size +
                ", buckets=" + Arrays.toString(buckets) +
                '}';
    }
}
