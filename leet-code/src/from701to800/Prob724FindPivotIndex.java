package from701to800;

// https://leetcode.com/problems/find-pivot-index/
public class Prob724FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int len = nums.length;

        // partial[i] = nums[0] + nums[1] + ... + nums[i]
        int[] partial = new int[len];
        partial[0] = nums[0];
        for (int i = 1; i < len; i++) partial[i] = partial[i - 1] + nums[i];

        int leftSum, rightSum;
        for (int i = 0; i < len; i++) {
            leftSum = i == 0 ? 0 : partial[i - 1];
            rightSum = partial[len - 1] - partial[i];

            if (leftSum == rightSum) return i;
        }

        return -1;
    }
}
