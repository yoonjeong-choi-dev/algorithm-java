package from1to100;

// https://leetcode.com/problems/jump-game-ii/
public class Prob45JumpGame2 {
    public int jump(int[] nums) {
        int len = nums.length - 1;

        if (len == 0) return 0;

        int ans = 1;

        int lastIdx = nums[0];
        int curIdx = 0;
        int prev;
        while (lastIdx < len) {
            prev = lastIdx;
            while (curIdx <= prev) {
                lastIdx = Math.max(lastIdx, curIdx + nums[curIdx]);
                curIdx++;
            }

            ans++;
        }

        return ans;
    }
}
