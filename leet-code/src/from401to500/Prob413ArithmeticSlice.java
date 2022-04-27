package from401to500;

// https://leetcode.com/problems/arithmetic-slices/
public class Prob413ArithmeticSlice {
    public int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        if (len < 3) return 0;

        // dp[i] : i 부터 시작하는 증가 배열의 개수
        int[] dp = new int[len];
        dp[len - 1] = 0;
        dp[len - 2] = 0;

        int ans = 0;
        for (int i = len - 3; i >= 0; i--) {
            if (nums[i] - nums[i + 1] == nums[i + 1] - nums[i + 2]) {
                dp[i] = 1 + dp[i + 1];
            } else {
                dp[i] = 0;
            }

            ans += dp[i];
        }

        return ans;
    }
}
