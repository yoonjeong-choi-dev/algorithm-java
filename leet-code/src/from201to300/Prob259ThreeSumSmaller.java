package from201to300;

import java.util.Arrays;

// https://leetcode.com/problems/3sum-smaller/
public class Prob259ThreeSumSmaller {
    private int[] nums;
    private int len;

    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        this.nums = nums;
        this.len = nums.length;

        int ans = 0;
        for (int i = 0; i < len - 2; i++) {
            ans += twoSumSmaller(i + 1, target - nums[i]);
        }


        return ans;
    }

    private int twoSumSmaller(int start, int target) {

        int ans = 0;
        int left = start, right = len - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                // nums[left] + nums[i] < target where i <= right
                // left 기준으로 (left+1, ..., right) 만큼 가능 => right - (left+1) + 1 = right - left
                ans += right - left;
                left++;
            } else {
                right--;
            }
        }

        return ans;
    }
}
