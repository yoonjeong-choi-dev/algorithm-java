package from801to900;

import java.util.*;

// https://leetcode.com/problems/making-a-large-island/
public class Prob847MakingALargestIsland {

    private static final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private int rowSize, colSize;
    private int[][] grid;
    private int[][] component;

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        rowSize = grid.length;
        colSize = grid[0].length;

        component = new int[rowSize][colSize];
        for (int[] row : component) Arrays.fill(row, -1);

        List<Integer> area = new ArrayList<>();

        // Step 1 : DFS to find all components
        int ans = 0;
        int curArea;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == 1 && component[i][j] == -1) {
                    curArea = dfsArea(i, j, area.size());
                    area.add(curArea);
                    ans = Math.max(ans, curArea);
                }
            }
        }

        // Step 2 : Add a land
        int x, y;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == 1) continue;

                // 상하좌우 인접 컴포넌트
                Set<Integer> neighbor = new HashSet<>();
                for (int[] d : direction) {
                    x = i + d[0];
                    y = j + d[1];

                    if (x >= 0 && x < rowSize && y >= 0 && y < colSize && component[x][y] != -1) {
                        neighbor.add(component[x][y]);
                    }
                }

                curArea = 1;
                for (int n : neighbor) curArea += area.get(n);
                ans = Math.max(ans, curArea);
            }
        }
        return ans;
    }

    private int dfsArea(int x, int y, int curComp) {
        // visit
        int ans = 1;
        component[x][y] = curComp;

        int nextX, nextY;
        for (int[] d : direction) {
            nextX = x + d[0];
            nextY = y + d[1];
            if (nextX < 0 || nextX >= rowSize || nextY < 0 || nextY >= colSize) continue;

            if (grid[nextX][nextY] == 1 && component[nextX][nextY] == -1) ans += dfsArea(nextX, nextY, curComp);
        }

        return ans;
    }
}
