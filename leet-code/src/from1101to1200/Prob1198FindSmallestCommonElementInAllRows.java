package from1101to1200;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/find-smallest-common-element-in-all-rows/
public class Prob1198FindSmallestCommonElementInAllRows {
    public int smallestCommonElement(int[][] mat) {
        // value -> number of occurrence
        Map<Integer, Integer> numOccurs = new HashMap<>();
        for (int[] row : mat) {
            for (int val : row) numOccurs.put(val, numOccurs.getOrDefault(val, 0) + 1);
        }

        int target = mat.length;
        int ans = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : numOccurs.entrySet()) {
            if (entry.getValue() == target) ans = Math.min(ans, entry.getKey());
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
