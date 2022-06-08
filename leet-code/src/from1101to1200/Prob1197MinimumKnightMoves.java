package from1101to1200;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/minimum-knight-moves/
public class Prob1197MinimumKnightMoves {

    private static final int[][] directions = {
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2},
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1}
    };

    public int minKnightMoves(int x, int y) {
        // -300<=x,y<=300 && |x|+|y| <= 300
        // => max(|x|,|y|) <= 302 : 외부에서 내부로 돌아와서 배치되는 경우
        final int MAX_BOUNDARY = 302;
        final int BOARD_SIZE = MAX_BOUNDARY * 2 + 1;
        x = x + MAX_BOUNDARY;
        y = y + MAX_BOUNDARY;

        boolean[][] isVisited = new boolean[BOARD_SIZE][BOARD_SIZE];

        Queue<int[]> bfs = new LinkedList<>();
        bfs.add(new int[]{MAX_BOUNDARY, MAX_BOUNDARY});
        isVisited[MAX_BOUNDARY][MAX_BOUNDARY] = true;


        int steps = 0;
        int[] curPos;
        int nextX, nextY;
        while (!bfs.isEmpty()) {
            for (int i = bfs.size() - 1; i >= 0; i--) {
                curPos = bfs.poll();
                if (curPos[0] == x && curPos[1] == y) return steps;

                for (int[] d : directions) {
                    nextX = curPos[0] + d[0];
                    nextY = curPos[1] + d[1];

                    if (nextX < 0 || nextX >= BOARD_SIZE || nextY < 0 || nextY >= BOARD_SIZE) continue;

                    if (!isVisited[nextX][nextY]) {
                        isVisited[nextX][nextY] = true;
                        bfs.add(new int[]{nextX, nextY});
                    }
                }
            }

            steps++;
        }

        // Never Reach : It is guaranteed the answer exists
        return -1;
    }
}
