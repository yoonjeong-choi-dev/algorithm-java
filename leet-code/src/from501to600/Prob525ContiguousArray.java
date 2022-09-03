package from501to600;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/contiguous-array/
// Ref : Problem 325
public class Prob525ContiguousArray {
    public int findMaxLength(int[] nums) {
        // sum of( nums[:i] ) -> i
        // 이때 0은 -1로 잡아준다
        Map<Integer, Integer> prefixIndex = new HashMap<>();

        int ans = 0;
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i] == 0 ? -1 : 1;

            if (curSum == 0) {
                ans = i + 1;
            } else {
                ans = Math.max(ans, i - prefixIndex.getOrDefault(curSum, i));
            }

            if (!prefixIndex.containsKey(curSum)) prefixIndex.put(curSum, i);
        }
        return ans;
    }
}
