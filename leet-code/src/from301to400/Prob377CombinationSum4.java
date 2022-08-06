package from301to400;

import java.util.Arrays;

// https://leetcode.com/problems/combination-sum-iv/
public class Prob377CombinationSum4 {

    private int[] nums;
    private int[] cache;

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        this.nums = nums;
        this.cache = new int[target + 1];
        Arrays.fill(cache, -1);

        return recur(target);
    }

    private int recur(int remain) {
        if (remain == 0) return 1;

        if (cache[remain] != -1) return cache[remain];

        int ans = 0;
        for (int num : nums) {
            if (num > remain) break;
            ans += recur(remain - num);
        }

        return cache[remain] = ans;
    }


    private int dpSolution(int[] nums, int target) {
        Arrays.sort(nums);
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int curSum = 1; curSum <= target; curSum++) {
            for (int num : nums) {
                if(num > curSum) break;
                dp[curSum] += dp[curSum - num];
            }
        }

        return dp[target];
    }
}
