package from401to500;

// https://leetcode.com/problems/max-consecutive-ones-ii/
public class Prob487MaxConsecutiveOnes2 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int size = nums.length;
        if (size == 1) return 1;

        int ans = 1;
        int zeroIdx = -1;
        int left = 0, right = 0;

        while (right < size) {
            // nums[left:right] 까지의 부분 배열
            if (nums[right] == 0) {
                // 현재 숫자가 0인 경우, 현재 숫자를 변경해야 하므로 이전 0 인덱스 이후부터 시작해야 함
                left = zeroIdx + 1;
                zeroIdx = right;
            }

            right++;
            ans = Math.max(ans, right - left);
        }

        return ans;
    }
}
