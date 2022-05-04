package from1601to1700;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/max-number-of-k-sum-pairs/
public class Prob1679MaxNumberOfKSumPairs {
    public int maxOperations(int[] nums, int k) {
        int size = nums.length;
        Map<Integer, Integer> occurs = new HashMap<>();

        int remain;
        int ans = 0;
        for (int i = 0; i < size; i++) {
            remain = k - nums[i];
            if (occurs.getOrDefault(remain, 0) == 0) {
                occurs.put(nums[i], occurs.getOrDefault(nums[i], 0) +1);
            } else {
                occurs.put(remain, occurs.get(remain) - 1);
                ans++;
            }
        }
        return ans;
    }
}
