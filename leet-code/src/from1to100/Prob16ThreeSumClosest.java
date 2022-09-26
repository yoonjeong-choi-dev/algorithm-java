package from1to100;

import java.util.Arrays;

// https://leetcode.com/problems/3sum-closest/
public class Prob16ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int bestDiff = Integer.MAX_VALUE;
        int len = nums.length;

        int left, right, curDiff;
        for (int i = 0; i < len; i++) {
            left = i + 1;
            right = len - 1;

            while (left < right) {
                curDiff = target - (nums[i] + nums[left] + nums[right]);
                if (curDiff == 0) return target;

                if (Math.abs(curDiff) < Math.abs(bestDiff)) {
                    bestDiff = curDiff;
                }

                if (curDiff < 0) right--;
                else left++;
            }
        }

        return target - bestDiff;
    }
}
