package from1to100;

// https://leetcode.com/problems/jump-game/
public class Prob55JumpGame {
    public boolean canJump(int[] nums) {
        int len = nums.length - 1;

        if (len == 0) return true;

        int lastIdx = nums[0];

        int curIdx = 0;
        int prev;
        while (lastIdx < len) {
            prev = lastIdx;
            while (curIdx <= prev) {
                lastIdx = Math.max(lastIdx, curIdx + nums[curIdx]);
                curIdx++;
            }

            if (prev == lastIdx) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Prob55JumpGame sol = new Prob55JumpGame();

        int[][] nums = {{2, 3, 1, 1, 4}, {3, 2, 1, 0, 4}, {2, 0, 0}, {2, 5, 0, 0}};

        boolean[] ans = {true, false, true, true};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            boolean mySol = sol.canJump(nums[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
