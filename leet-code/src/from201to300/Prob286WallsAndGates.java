package from201to300;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/walls-and-gates/
public class Prob286WallsAndGates {
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int empty = 2147483647;
    private static final int gate = 0;

    public void wallsAndGates(int[][] rooms) {
        int rowSize = rooms.length;
        int colSize = rooms[0].length;

        int row, col;

        Queue<int[]> queue = new ArrayDeque<>();

        // 시작점들(게이트) 큐에 저장
        for (row = 0; row < rowSize; row++) {
            for (col = 0; col < colSize; col++) {
                if (rooms[row][col] == gate) {
                    queue.offer(new int[]{row, col});
                }
            }
        }

        // bfs
        int[] curPos;
        int nextRow, nextCol;
        while (!queue.isEmpty()) {
            curPos = queue.poll();
            row = curPos[0];
            col = curPos[1];

            for (int[] d : directions) {
                nextRow = row + d[0];
                nextCol = col + d[1];

                if (nextRow >= 0 && nextRow < rowSize && nextCol >= 0 && nextCol < colSize && rooms[nextRow][nextCol] == empty) {
                    rooms[nextRow][nextCol] = rooms[row][col] + 1;
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
        }
    }
}
