package from1to100;

// https://leetcode.com/problems/minimum-path-sum/
public class Prob64MinimumPathSum {
    // You can only move either down or right at any point in time.
    public int minPathSum(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int temp;

        // update bottom
        temp = row - 1;
        for (int i = col - 2; i >= 0; i--) {
            grid[temp][i] += grid[temp][i + 1];
        }

        // update right
        temp = col - 1;
        for (int i = row - 2; i >= 0; i--) {
            grid[i][temp] += grid[i + 1][temp];
        }

        // update from bottom-right to top-left
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                // 우하단으로만 이동 가능 => 오른쪽 및 아래쪽 정보만 필요
                grid[i][j] += Math.min(grid[i + 1][j], grid[i][j + 1]);
            }
        }


        return grid[0][0];
    }
}
