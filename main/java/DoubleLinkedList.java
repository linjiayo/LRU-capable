class DoubleLinkedList<K, V> {
    DNode<K, V> headDummy;
    DNode<K, V> tailDummy;
    int size;


    public DoubleLinkedList() {
        this.headDummy = new DNode<>();
        this.tailDummy = new DNode<>();
        size = 0;
        this.headDummy.right = tailDummy;
        this.tailDummy.left = headDummy;
    }

    // insert to head
    void add(DNode<K, V> node) {
        node.right = headDummy.right;
        this.headDummy.right.left = node;
        this.headDummy.right = node;
        node.left = headDummy;
        size++;
    }


    void remove(DNode<K, V> node) {
        node.left.right = node.right;
        node.right.left = node.left;
        size--;
    }


    void removeTail() {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            this.headDummy.right = this.tailDummy;
            this.tailDummy.left = this.headDummy;
            size--;
            return;
        }
        remove(tailDummy.left);
    }

}
