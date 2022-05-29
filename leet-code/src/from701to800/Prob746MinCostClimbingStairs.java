package from701to800;

// https://leetcode.com/problems/min-cost-climbing-stairs/
public class Prob746MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        // 현재 계단의 이전 계단 및 이전 이전 계단까지 도달했을 때 최소 비용
        // 문제 조건 : len >= 2
        int len = cost.length;

        // f(n) = min(f(n-1), f(n-2)) + cost[n]
        for (int i = 2; i < len; i++) {
            cost[i] = Math.min(cost[i - 1], cost[i - 2]) + cost[i];
        }

        return Math.min(cost[len - 2], cost[len - 1]);
    }

    public static void main(String[] args) {
        Prob746MinCostClimbingStairs sol = new Prob746MinCostClimbingStairs();

        int[][] costs = {
                {10, 15, 20},
                {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}
        };

        int[] ans = {15, 6};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.minCostClimbingStairs(costs[i]);
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
