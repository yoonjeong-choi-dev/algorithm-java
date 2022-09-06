package from1701to1800;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/shortest-path-to-get-food/
public class Prob1730ShortestPathToGetFood {

    private static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int getFood(char[][] grid) {
        int rowSize = grid.length, colSize = grid[0].length;

        int[] start = new int[2];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == '*') {
                    start[0] = i;
                    start[1] = j;

                    grid[i][j] = 'X';
                    break;
                }
            }
        }

        // BFS
        Queue<int[]> bfs = new ArrayDeque<>();
        bfs.add(start);

        int ans = 0;
        int[] cur;
        int x, y;
        while (!bfs.isEmpty()) {
            ans++;

            for (int i = bfs.size(); i > 0; i--) {
                cur = bfs.poll();
                for (int[] d : dirs) {
                    x = cur[0] + d[0];
                    y = cur[1] + d[1];

                    if (x < 0 || x >= rowSize || y < 0 || y >= colSize) continue;

                    if (grid[x][y] == '#') return ans;

                    if (grid[x][y] == 'O') {
                        grid[x][y] = 'X';
                        bfs.add(new int[]{x, y});
                    }
                }
            }

        }

        return -1;
    }
}
