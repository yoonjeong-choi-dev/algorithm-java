package from201to300;

// https://leetcode.com/problems/maximal-square/
public class Prob221MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int rowSize = matrix.length, colSize = matrix[0].length;

        // dp[i][j] : (i,j) 부터 시작하는 정사각형의 크기
        int[][] dp = new int[rowSize][colSize];

        // Update bottom and left
        int temp = rowSize - 1;
        int ans = 0;
        for (int i = 0; i < colSize; i++) {
            if (matrix[temp][i] == '1') {
                dp[temp][i] = 1;
                ans = 1;
            }
        }

        temp = colSize - 1;
        for (int i = 0; i < rowSize; i++) {
            if (matrix[i][temp] == '1') {
                dp[i][temp] = 1;
                ans = 1;
            }
        }

        // dp[row][col] = 1+ min(dp[row+1][col+1], dp[row+1][col], dp[row][col+1]
        for (int row = rowSize - 2; row >= 0; row--) {
            for (int col = colSize - 2; col >= 0; col--) {
                if (matrix[row][col] == '1') {
                    dp[row][col] = 1 + Math.min(dp[row + 1][col + 1], Math.min(dp[row + 1][col], dp[row][col + 1]));
                    ans = Math.max(dp[row][col], ans);
                }
            }
        }

        return ans * ans;
    }
}
