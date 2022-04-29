package from301to400;

import java.util.Arrays;

// https://leetcode.com/problems/coin-change/
public class Prob322CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;

        // 가장 작은 돈부터 고려
        Arrays.sort(coins);

        // dp[i] : amount==i 일 때의 부분 문제
        int upperBound = Integer.MAX_VALUE - 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, upperBound);

        for (int i = 1; i <= amount; i++) {

            for (int coin : coins) {
                if (i < coin) continue;
                if (i == coin) {
                    dp[i] = 1;
                    break;
                }

                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == upperBound ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        Prob322CoinChange sol = new Prob322CoinChange();
        sol.coinChange(new int[]{1, 2, 5}, 11);
    }
}
