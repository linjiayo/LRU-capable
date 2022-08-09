public class DNode<K, V> {
    K key;
    V value;
    public int freq;
    DNode<K, V> left;
    DNode<K, V> right;
    public DNode() {
        freq = 1;
    }
    public DNode(K key, V value) {
        this.key = key;
        this.value = value;
        freq = 1;
    }
}
