package from901to1000;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

// https://leetcode.com/problems/shortest-bridge/
public class Prob934ShortestBridge {

    private static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int shortestBridge(int[][] grid) {
        int rowSize = grid.length, colSize = grid[0].length;

        // dfs to find one island
        Stack<int[]> dfs = new Stack<>();
        boolean isFound = false;

        // save the island coordinate for bfs later
        Queue<int[]> bfs = new ArrayDeque<>();

        int[] cur;
        int x, y;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = -1;
                    dfs.push(new int[]{i, j});
                    while (!dfs.isEmpty()) {
                        cur = dfs.pop();
                        bfs.add(cur);

                        for (int[] d : dirs) {
                            x = cur[0] + d[0];
                            y = cur[1] + d[1];

                            if (x < 0 || x >= rowSize || y < 0 || y >= colSize) continue;

                            if (grid[x][y] == 1) {
                                grid[x][y] = -1;
                                dfs.push(new int[]{x, y});
                            }
                        }
                    }


                    isFound = true;
                    break;

                }
            }

            if (isFound) break;
        }

        // bfs to calculate the distance
        // => 두 섬의 거리 -1
        int dist = 0;
        while (!bfs.isEmpty()) {
            for (int i = bfs.size() - 1; i >= 0; i--) {
                cur = bfs.poll();
                for (int[] d : dirs) {
                    x = cur[0] + d[0];
                    y = cur[1] + d[1];

                    if (x < 0 || x >= rowSize || y < 0 || y >= colSize) continue;

                    if (grid[x][y] == 0) {
                        grid[x][y] = -1;
                        bfs.add(new int[]{x, y});
                    } else if (grid[x][y] == 1) {
                        // Meet another island
                        return dist;
                    }
                }
            }

            dist++;
        }

        // never reach
        return -1;
    }
}
