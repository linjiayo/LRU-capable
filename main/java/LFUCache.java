import java.util.HashMap;
import java.util.Map;

public class LFUCache<K, V> {
    int capacity;
    Map<K, DNode<K, V>> nodeMap;
    Map<Integer, DoubleLinkedList<K, V>> freqMap;
    int leastFrequency;
    int size;


    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.leastFrequency = 0;
        nodeMap = new HashMap<>();
        freqMap = new HashMap<>();
        size = 0;
    }

    public V get(K key) {
        if (!nodeMap.containsKey(key)) {
            return null;
        }
        DNode<K, V> res = nodeMap.get(key);
        update(res);
        return res.value;
    }


    public void put(K key, V value) {
        if (capacity == 0) {
            return;
        }
        if (nodeMap.containsKey(key)) {
            DNode<K, V> node = nodeMap.get(key);
            update(node);
            node.value = value;
            if (node.freq == leastFrequency && size == 1) {
                leastFrequency++;
            }
            return;
        }

        if (size == capacity) {
            DoubleLinkedList<K, V> leastUsed = freqMap.get(leastFrequency);
            nodeMap.remove(leastUsed.tailDummy.left.key);
            leastUsed.removeTail();
            size--;
        }

        DNode<K, V> newNode = new DNode<>(key, value);
        nodeMap.put(key, newNode);

        leastFrequency = newNode.freq;

        if (!freqMap.containsKey(leastFrequency)) {
            freqMap.put(leastFrequency, new DoubleLinkedList<>());
        }
        DoubleLinkedList<K, V> freqList = freqMap.get(leastFrequency);
        freqList.add(newNode);
        size++;

    }


    // Update node's frequency map
    private void update(DNode<K, V> node) {
        DoubleLinkedList<K, V> freqList = freqMap.get(node.freq);
        freqList.remove(node);

        if (node.freq == leastFrequency && freqList.size == 0) {
            leastFrequency++;
        }

        node.freq++;
        freqMap.put(node.freq, freqMap.getOrDefault(node.freq, new DoubleLinkedList<>()));

        DoubleLinkedList<K, V> newList = freqMap.get(node.freq);
        newList.add(node);
    }
}
