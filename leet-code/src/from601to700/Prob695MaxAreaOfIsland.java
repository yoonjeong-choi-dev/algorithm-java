package from601to700;

// https://leetcode.com/problems/max-area-of-island/
public class Prob695MaxAreaOfIsland {

    private int rowSize, colSize;

    public int maxAreaOfIsland(int[][] grid) {
        rowSize = grid.length;
        colSize = grid[0].length;

        int ans = 0;

        int curArea;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                // 방문하지 않은 땅에 대해서 dfs
                if (grid[i][j] == 1) {
                    curArea = dfs(grid, i, j);
                    ans = Math.max(ans, curArea);
                }
            }
        }

        return ans;
    }

    private int dfs(int[][] grid, int row, int col) {
        // Base Case 1 : 범위 밖으로 벗어난 경우
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize) return 0;

        // Base Case 2 : 해당 지역이 물인 경우
        if (grid[row][col] == 0) return 0;

        // 현재 지역 방문
        int ans = 1;
        grid[row][col] = 0;

        // 인접 지역에 대해서 dfs
        ans += dfs(grid, row-1, col);
        ans += dfs(grid, row + 1, col);
        ans += dfs(grid, row, col - 1);
        ans += dfs(grid, row, col + 1);

        return ans;
    }

    public static void main(String[] args) {
        Prob695MaxAreaOfIsland sol = new Prob695MaxAreaOfIsland();

        int[][][] grids = {
                {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}},
                {{0, 0, 0, 0, 0, 0, 0, 0}}
        };

        int[] ans = {6, 0};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.maxAreaOfIsland(grids[i]);
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
