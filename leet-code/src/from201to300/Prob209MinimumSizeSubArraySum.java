package from201to300;

// https://leetcode.com/problems/minimum-size-subarray-sum/
public class Prob209MinimumSizeSubArraySum {
    public int minSubArrayLen(int target, int[] nums) {
        return myLinearSolution(target, nums);
    }

    // Ref : Problem 713
    private int myLinearSolution(int target, int[] nums) {
        int curSum = 0;
        int ans = Integer.MAX_VALUE;

        int start = 0;
        for (int end = 0; end < nums.length; end++) {
            // [start:end] 배열의 합
            curSum += nums[end];

            // 타겟보다 작아질 때까지 시작 인덱스 이동하면서 정답 업데이트
            while (curSum >= target) {
                ans = Math.min(ans, end - start + 1);
                curSum -= nums[start++];
            }
        }

        if (ans == Integer.MAX_VALUE) ans = 0;

        return ans;
    }

    public static void main(String[] args) {
        Prob209MinimumSizeSubArraySum sol = new Prob209MinimumSizeSubArraySum();

        int[][] nums = {
                {2, 3, 1, 2, 4, 3},
                {1, 4, 4},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 2, 3, 4, 5}
        };

        int[] targets = {7, 4, 11, 11};

        int[] ans = {2, 1, 0, 3};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.minSubArrayLen(targets[i], nums[i]);
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
