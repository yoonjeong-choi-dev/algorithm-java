package from1001to1100;

import java.util.Arrays;

// https://leetcode.com/problems/two-sum-less-than-k/
public class Prob1099TwoSumLessThanK {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = -1;

        int left = 0, right = nums.length - 1;
        int curSum;
        while (left < right) {
            curSum = nums[left] + nums[right];
            if (curSum < k) {
                ans = Math.max(ans, curSum);
                left++;
            } else {
                right--;
            }
        }

        return ans;
    }
}
