package from1601to1700;

// https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
public class Prob1658MinimumOperationsToReduceXToZero {
    public int minOperations(int[] nums, int x) {
        int len = nums.length;

        // nums[left:right] 부분 배열의 합
        int target = -x;
        for (int num : nums) target += num;

        final int NULL_VALE = Integer.MIN_VALUE;
        int ans = NULL_VALE;

        int left = 0;
        int curSum = 0;
        for (int right = 0; right < len; right++) {
            // nums[right] 추가
            curSum += nums[right];

            // target 값 이하가 되도록 left 이동
            while (curSum > target && left <= right) {
                curSum -= nums[left++];
            }

            // left~right 까지의 합이 타겟과 같은 경우 => 부분 배열 길이 업데이트
            if (curSum == target) {
                ans = Math.max(ans, right - left + 1);
            }
        }


        return ans == NULL_VALE ? -1 : len - ans;
    }
}
