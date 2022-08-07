package from1201to1300;

import java.util.Arrays;

// https://leetcode.com/problems/count-vowels-permutation/
public class Prob1220CountVowelsPermutation {
    public int countVowelPermutation(int n) {
        // Rules
        // a -> e, e -> (a,i), i -> (a,e,o,u), o -> (i,u), u -> a
        // 1. (e,i,u) -> a
        // 2. (a,i) -> e
        // 3. (e,o) -> i
        // 4. (i) -> o
        // 5. (i,o) -> u
        final int MOD = 1000000007;

        // dp : (a,e,i,o,u) 로 끝나는 문자열의 경우의 수
        int[] dp = new int[5];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            int[] next = new int[5];

            // (e,i,u) -> a
            next[0] = ((dp[1] + dp[2]) % MOD + dp[4]) % MOD;

            // (a,i) -> e
            next[1] = (dp[0] + dp[2]) % MOD;

            // (e,o) -> i
            next[2] = (dp[1] + dp[3]) % MOD;

            // i -> o
            next[3] = dp[2];

            // (i,o) -> u
            next[4] = (dp[2] + dp[3]) % MOD;

            dp = next;
        }

        int ans = 0;
        for (int i = 0; i < 5; i++) {
            ans = (ans + dp[i]) % MOD;
        }
        return ans;
    }
}
