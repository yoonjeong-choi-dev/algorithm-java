package from1401to1500;

// https://leetcode.com/problems/running-sum-of-1d-array/
public class Prob1480RunningSumOf1DArray {
    public int[] runningSum(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];

        ans[0] = nums[0];
        for (int i = 1; i < len; i++) ans[i] = ans[i - 1] + nums[i];
        return ans;
    }
}
