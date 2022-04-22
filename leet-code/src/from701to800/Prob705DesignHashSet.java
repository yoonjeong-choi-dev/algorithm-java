package from701to800;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/design-hashset/
public class Prob705DesignHashSet {
    class HashBucket {
        private final List<Integer> bucket = new LinkedList<>();

        public HashBucket() {
        }

        public void add(Integer key) {
            if (bucket.contains(key)) return;
            bucket.add(key);
        }

        public void remove(Integer key) {
            bucket.remove(key);
        }

        public boolean contains(Integer key) {
            return bucket.contains(key);
        }
    }

    class MyHashSet {

        private final int hashMod;
        private final HashBucket[] buckets;

        public MyHashSet() {
            hashMod = 999;
            buckets = new HashBucket[hashMod];
            for (int i = 0; i < hashMod; i++) {
                buckets[i] = new HashBucket();
            }
        }

        public void add(int key) {
            int hash = hashFunction(key);
            buckets[hash].add(key);
        }

        public void remove(int key) {
            int hash = hashFunction(key);
            buckets[hash].remove(key);
        }

        public boolean contains(int key) {
            int hash = hashFunction(key);
            return buckets[hash].contains(key);
        }

        private int hashFunction(int item) {
            return item % hashMod;
        }
    }
}
