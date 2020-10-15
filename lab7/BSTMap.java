import java.util.Iterator;
import java.util.Set;

public class BSTMap<K,V> implements Map61B<K, V>{

    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int size;

        public Node (K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (x == null ) return null;
        int cmp = key.hashCode() - x.key.hashCode();
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x){
        if (x == null) return 0;
        else return x.size;
    }

    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    private Node put(K key, V value, Node x) {
        if (x == null) return new Node(key, value, 1);
        int cmp = key.hashCode() - x.key.hashCode();
        if (cmp < 0) x.left = put(key, value, x.left);
        else if (cmp > 0) x.right = put(key, value, x. right);
        else x.value = value;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }


    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        for (K key: this.keySet()) {
            System.out.println("Key: " + key + "; value: " + get(key));
        }
    }

}
