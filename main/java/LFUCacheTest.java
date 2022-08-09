import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LFUCacheTest {

    @Test
    public void testLFUCache() {
        LFUCache<Integer, Integer> lfu = new LFUCache<>(2);

        lfu.put(1, 1);
        lfu.put(2, 2);
        lfu.put(3, 3);
        lfu.put(4, 4);

        Integer actualLeastRecent2 = lfu.get(2);
        Integer actualLeastRecent1 = lfu.get(1);
        Integer actualRecent1 = lfu.get(3);
        Integer actualRecent2 = lfu.get(4);

        Assertions.assertNull(actualLeastRecent1);
        Assertions.assertNull(actualLeastRecent2);
        Assertions.assertEquals(3, actualRecent1);
        Assertions.assertEquals(4, actualRecent2);

        // increase frequency with get calls
        lfu.get(3);
        lfu.get(3);
        lfu.get(3);

        lfu.put(5, 5);

        Integer actualLeastFrequent = lfu.get(4);
        Integer actualMostFrequent = lfu.get(3);

        Assertions.assertNull(actualLeastFrequent);
        Assertions.assertEquals(3, actualMostFrequent);
    }

}