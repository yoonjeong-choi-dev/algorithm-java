package from401to500;

// https://leetcode.com/problems/max-consecutive-ones/
public class Prob485MaxConsecutiveOnes1 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0;
        int curOnes = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                ans = Math.max(ans, curOnes);
                curOnes = 0;
            } else {
                curOnes++;
            }
        }
        ans = Math.max(ans, curOnes);

        return ans;
    }
}
