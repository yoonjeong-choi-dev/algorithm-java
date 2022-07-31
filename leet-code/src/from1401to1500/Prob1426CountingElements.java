package from1401to1500;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/counting-elements/
public class Prob1426CountingElements {
    public int countElements(int[] arr) {
        Map<Integer, Integer> counters = new HashMap<>();
        for(int n : arr) {
            counters.put(n, counters.getOrDefault(n, 0) + 1);
        }

        int ans = 0;
        for(int curNum : counters.keySet()) {
            if(counters.containsKey(curNum+1)) ans += counters.get(curNum);
        }

        return ans;
    }
}
