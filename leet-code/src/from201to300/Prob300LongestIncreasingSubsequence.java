package from201to300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/longest-increasing-subsequence/
public class Prob300LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        return solutionWithDP(nums);
    }

    private int solutionWithDP(int[] nums) {
        int len = nums.length;

        // dp[i] : nums[0:i] 부분 배열의 정답
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) dp[i] = 1;

        for (int i = 1; i < len; i++) {
            // nums[0:i-1] 까지 부분 문제 탐색
            for (int j = 0; j < i; j++) {
                // nums[0:j] 의 정답 배열에 nums[i] 추가 가능한 경우
                if (nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int ans = dp[0];
        for (int i = 1; i < len; i++) ans = Math.max(ans, dp[i]);
        return ans;
    }

    // TODO : LIS 를 만드는 해결 방식

}
