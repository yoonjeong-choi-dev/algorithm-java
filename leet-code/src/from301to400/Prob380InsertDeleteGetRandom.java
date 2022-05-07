package from301to400;

import java.util.*;

// https://leetcode.com/problems/insert-delete-getrandom-o1/
public class Prob380InsertDeleteGetRandom {
    class RandomizedSet {

        private final Map<Integer, Integer> valToIndex = new HashMap<>();
        private final List<Integer> array = new ArrayList<>();
        private final Random random = new Random();

        public RandomizedSet() {

        }

        public boolean insert(int val) {
            if (valToIndex.containsKey(val)) return false;

            valToIndex.put(val, array.size());
            array.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!valToIndex.containsKey(val)) return false;

            // 삭제할 요소 위치에 가장 마지막 요소 교환
            int removeIdx = valToIndex.get(val);
            int lastVal = array.get(array.size() - 1);
            array.set(removeIdx, lastVal);
            valToIndex.put(lastVal, removeIdx);

            // 마지막 요소 삭제
            array.remove(array.size() - 1);
            valToIndex.remove(val);
            return true;
        }

        public int getRandom() {
            int randIdx = random.nextInt(array.size());
            return array.get(randIdx);
        }
    }
}
