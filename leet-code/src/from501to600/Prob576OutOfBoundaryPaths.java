package from501to600;

// https://leetcode.com/problems/out-of-boundary-paths/
public class Prob576OutOfBoundaryPaths {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        final int MOD = 1000000007;

        // dp[i][j] : (i,j) 로 가는 경우의 수
        int[][] dp = new int[m][n];
        dp[startRow][startColumn] = 1;

        int ans = 0;
        int[][] temp;
        for (int step = 0; step < maxMove; step++) {
            // 다음 스텝에 대한 경우의 수
            temp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // boundary 의 경우 정답 update : 상하좌우 각각 고려
                    if (i == 0) ans = (ans + dp[i][j]) % MOD;
                    if (i == m - 1) ans = (ans + dp[i][j]) % MOD;
                    if (j == 0) ans = (ans + dp[i][j]) % MOD;
                    if (j == n - 1) ans = (ans + dp[i][j]) % MOD;

                    // 경우의 수 업데이트
                    if (i > 0) temp[i][j] = (temp[i][j] + dp[i - 1][j]) % MOD;
                    if (i < m - 1) temp[i][j] = (temp[i][j] + dp[i + 1][j]) % MOD;
                    if (j > 0) temp[i][j] = (temp[i][j] + dp[i][j - 1]) % MOD;
                    if (j < n - 1) temp[i][j] = (temp[i][j] + dp[i][j + 1]) % MOD;
                }
            }

            dp = temp;
        }

        return ans;
    }
}
