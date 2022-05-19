package from1301to1400;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/
public class Prob1368MinimumCostToMakeAtLeastOneValidPathInGrid {

    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int minCost(int[][] grid) {
        int rowSize = grid.length, colSize = grid[0].length;

        int[][][] graph = new int[rowSize][colSize][4];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                int curDirection = grid[i][j] - 1;

                for (int d = 0; d < directions.length; d++) {
                    if (d != curDirection) {
                        graph[i][j][d] = 1;
                    }
                }
            }
        }

        int[][] dist = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                dist[i][j] = -1;
            }
        }

        // Dijkstra's algorithm
        // int[] - 0 : x, 1 : y 2 : dist
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        dist[0][0] = 0;
        queue.add(new int[]{0, 0, 0});
        int[] curInfo;
        int x, y, curDist, nextX, nextY, nextDist;
        while (!queue.isEmpty()) {
            curInfo = queue.poll();
            x = curInfo[0];
            y = curInfo[1];
            curDist = curInfo[2];

            // 이미 경로가 더 최단 거리면 무시
            if (dist[x][y] != -1 && dist[x][y] < curDist) continue;

            for (int i = 0; i < directions.length; i++) {
                nextX = x + directions[i][0];
                nextY = y + directions[i][1];

                if (nextX < 0 || nextX >= rowSize || nextY < 0 || nextY >= colSize) continue;

                nextDist = curDist + graph[x][y][i];

                // 짧은 경로 발견 시 큐에 저장
                if (dist[nextX][nextY] == -1 || dist[nextX][nextY] > nextDist) {
                    dist[nextX][nextY] = nextDist;
                    queue.add(new int[]{nextX, nextY, nextDist});
                }
            }
        }

        return dist[rowSize - 1][colSize - 1];
    }
}