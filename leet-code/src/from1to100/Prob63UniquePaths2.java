package from1to100;

// https://leetcode.com/problems/unique-paths-ii/
public class Prob63UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length, col = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1 || obstacleGrid[row - 1][col - 1] == 1) return 0;

        // 장애물 값을 -1로 변경
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                obstacleGrid[i][j] *= -1;

        for (int i = 0; i < row; i++) {
            if (obstacleGrid[i][0] == -1) break;
            obstacleGrid[i][0] = 1;
        }

        for (int i = 0; i < col; i++) {
            if (obstacleGrid[0][i] == -1) break;
            obstacleGrid[0][i] = 1;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == -1) continue;

                if (obstacleGrid[i - 1][j] != -1) obstacleGrid[i][j] += obstacleGrid[i - 1][j];
                if (obstacleGrid[i][j - 1] != -1) obstacleGrid[i][j] += obstacleGrid[i][j - 1];
            }
        }


        return obstacleGrid[row - 1][col - 1];
    }
}
