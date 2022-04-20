package from1001to1100;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/shortest-path-in-binary-matrix/
public class Prob1091ShortedPathInBinaryMatrix {
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int size = grid.length;

        if (size == 1) return grid[0][0] == 0 ? 1 : -1;

        // 시작점이나 끝점을 방문하지 않은 경우 -1
        if (grid[0][0] == 1 || grid[size - 1][size - 1] == 1) return -1;

        // grid 에 거리도 저장하기 위해서 방문하지 않은 위치의 값을 -1 로 변경
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == 1) grid[i][j] = -1;
            }
        }

        // bfs
        Queue<int[]> bfs = new LinkedList<>();
        bfs.offer(new int[]{0, 0});

        int nextX, nextY, nextDist;
        int[] curPos;
        while (!bfs.isEmpty()) {
            curPos = bfs.poll();
            nextDist = grid[curPos[0]][curPos[1]] + 1;

            // 8 방향에 대해서 탐색
            for (int[] d : directions) {
                nextX = curPos[0] + d[0];
                nextY = curPos[1] + d[1];
                if (nextX < 0 || nextX >= size || nextY < 0 || nextY >= size) continue;

                // 거리 계산을 하지 않은 경우에만 저장
                if (grid[nextX][nextY] == 0) {
                    grid[nextX][nextY] = nextDist;
                    bfs.offer(new int[]{nextX, nextY});
                }
            }
        }

        return grid[size - 1][size - 1] == 0 ? -1 : grid[size - 1][size - 1] + 1;
    }
}
