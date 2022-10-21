package from201to300;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/contains-duplicate-ii/
public class Prob219ContainsDuplicate2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> valToIdx = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (valToIdx.containsKey(nums[i]) && i - valToIdx.get(nums[i]) <= k) return true;

            valToIdx.put(nums[i], i);
        }
        return false;
    }
}
