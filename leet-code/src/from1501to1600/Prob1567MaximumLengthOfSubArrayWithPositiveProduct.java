package from1501to1600;

// https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/
public class Prob1567MaximumLengthOfSubArrayWithPositiveProduct {
    public int getMaxLen(int[] nums) {
        int len = nums.length;
        int ans = 0;

        int start = 0, end;
        int numNegative, leftNegative, rightNegative;
        while (start < len) {
            // find non-zero
            while (start < len && nums[start] == 0) start++;

            end = start;
            numNegative = 0;
            leftNegative = rightNegative = -1;
            while (end < len && nums[end] != 0) {
                if (nums[end] < 0) {
                    numNegative++;
                    if (leftNegative == -1) leftNegative = end;
                    rightNegative = end;
                }

                end++;
            }

            if (numNegative % 2 == 0) {
                ans = Math.max(ans, end - start);
            } else {
                // 왼쪽이나 오른쪽 음수 제거
                ans = Math.max(ans, Math.max(end - leftNegative - 1, rightNegative - start));
            }

            start = end;
        }

        return ans;
    }
}
