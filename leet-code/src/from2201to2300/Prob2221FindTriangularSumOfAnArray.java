package from2201to2300;

// https://leetcode.com/problems/find-triangular-sum-of-an-array/
public class Prob2221FindTriangularSumOfAnArray {
    public int triangularSum(int[] nums) {
        for (int len = nums.length - 1; len > 0; len--) {
            for (int i = 0; i < len; i++) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
        }

        return nums[0];
    }
}
