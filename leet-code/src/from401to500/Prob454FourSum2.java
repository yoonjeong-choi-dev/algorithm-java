package from401to500;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/4sum-ii/
public class Prob454FourSum2 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int size = nums1.length;

        // (sum, 횟수)
        Map<Integer, Integer> firstPart = new HashMap<>();

        int curSum;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                curSum = nums1[i] + nums2[j];
                firstPart.put(curSum, firstPart.getOrDefault(curSum, 0) + 1);
            }
        }

        int ans = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                curSum = -nums3[i] - nums4[j];
                ans += firstPart.getOrDefault(curSum, 0);
            }
        }

        return ans;
    }
}
