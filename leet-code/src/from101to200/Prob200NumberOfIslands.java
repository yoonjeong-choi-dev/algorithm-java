package from101to200;

// https://leetcode.com/problems/number-of-islands/
public class Prob200NumberOfIslands {
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private char[][] grid;
    private int row, col;
    private boolean[][] isVisited;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        row = grid.length;
        col = grid[0].length;
        isVisited = new boolean[row][col];

        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1' && !isVisited[i][j]) {
                    ans++;
                    dfs(i, j);
                }
            }
        }

        return ans;
    }

    private void dfs(int x, int y) {
        isVisited[x][y] = true;

        int nextX, nextY;
        for (int[] d : directions) {
            nextX = x + d[0];
            nextY = y + d[1];

            if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= col) continue;

            if (grid[nextX][nextY] == '1' && !isVisited[nextX][nextY]) {
                dfs(nextX, nextY);
            }
        }
    }
}
