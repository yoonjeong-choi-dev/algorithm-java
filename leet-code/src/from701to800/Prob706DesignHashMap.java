package from701to800;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/design-hashmap/
public class Prob706DesignHashMap {
    class MyHashMap {
        final List<Integer> keyList = new LinkedList<>();
        final List<Integer> valueList = new LinkedList<>();

        public MyHashMap() {

        }

        public void put(int key, int value) {
            int idx = keyList.indexOf(key);
            if (idx == -1) {
                keyList.add(key);
                valueList.add(value);
            } else {
                valueList.set(idx, value);
            }
        }

        public int get(int key) {
            int idx = keyList.indexOf(key);
            if (idx == -1) {
                return -1;
            }
            return valueList.get(idx);
        }

        public void remove(int key) {
            int idx = keyList.indexOf(key);
            if (idx != -1) {
                keyList.remove(idx);
                valueList.remove(idx);
            }
        }
    }
}
