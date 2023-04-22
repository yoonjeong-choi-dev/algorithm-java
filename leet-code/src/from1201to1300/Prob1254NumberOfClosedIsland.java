package from1201to1300;

// https://leetcode.com/problems/number-of-closed-islands/
public class Prob1254NumberOfClosedIsland {

    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private int[][] grid;
    private int rowSize, colSize;
    private boolean[][] isVisited;

    public int closedIsland(int[][] grid) {

        // 땅 크기가 2*2 이하면 무조건 0
        if (grid.length < 3 || grid[0].length < 3) return 0;

        this.grid = grid;
        rowSize = grid.length;
        colSize = grid[0].length;

        isVisited = new boolean[rowSize][colSize];

        // 바운더리 처리 : 바운더리에 있는 0 들은 고려할 필요가 없음
        int lastIdx = colSize - 1;
        for (int i = 0; i < rowSize; i++) {
            if (grid[i][0] == 0 && !isVisited[i][0]) dfs(i, 0);
            if (grid[i][lastIdx] == 0 && !isVisited[i][lastIdx]) dfs(i, lastIdx);
        }

        lastIdx = rowSize - 1;
        for (int i = 1; i < colSize - 1; i++) {
            if (grid[0][i] == 0 && !isVisited[0][i]) dfs(0, i);
            if (grid[lastIdx][i] == 0 && !isVisited[lastIdx][i]) dfs(lastIdx, i);
        }

        int ans = 0;
        for (int row = rowSize - 2; row > 0; row--) {
            for (int col = colSize - 2; col > 0; col--) {
                if (grid[row][col] == 0 && !isVisited[row][col]) {
                    dfs(row, col);
                    ans++;
                }
            }
        }


        return ans;
    }

    private void dfs(int row, int col) {
        isVisited[row][col] = true;

        int nextRow, nextCol;
        for (int[] d : directions) {
            nextRow = row + d[0];
            nextCol = col + d[1];

            if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) continue;

            if (grid[nextRow][nextCol] == 0 && !isVisited[nextRow][nextCol]) {
                dfs(nextRow, nextCol);
            }
        }
    }
}
