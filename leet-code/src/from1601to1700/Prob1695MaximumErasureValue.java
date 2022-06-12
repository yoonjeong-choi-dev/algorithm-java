package from1601to1700;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-erasure-value/
// Ref : Problem 3
public class Prob1695MaximumErasureValue {
    public int maximumUniqueSubarray(int[] nums) {
        int len = nums.length;

        // leftIndex[i] : 현재 윈도우에서 i 숫자 개수
        // 1 <= nums[i] <= 10000
        int[] occurNums = new int[10001];

        int ans = 0, curSum = 0;
        int left = 0;

        int curNum, prevNum;
        for (int right = 0; right < len; right++) {
            curNum = nums[right];
            occurNums[curNum]++;
            curSum += curNum;

            // left 인덱스 이동하여 현재 숫자가 1번만 나오도록 한다
            while (left < right && occurNums[curNum] > 1) {
                prevNum = nums[left++];
                occurNums[prevNum]--;
                curSum -= prevNum;
            }

            // Update the answer
            ans = Math.max(ans, curSum);
        }

        return ans;
    }
}
