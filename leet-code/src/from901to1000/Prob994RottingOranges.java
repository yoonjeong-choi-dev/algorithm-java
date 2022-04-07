package from901to1000;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/rotting-oranges/
public class Prob994RottingOranges {

    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int orangesRotting(int[][] grid) {
        int row = grid.length, col = grid[0].length;

        int numFresh = 0;

        // 썪은 오렌지 위치 정보
        Queue<int[]> bfs = new ArrayDeque<>(row * col);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) numFresh++;
                else if (grid[i][j] == 2) bfs.offer(new int[]{i, j});
            }
        }

        // 각 타임스탬프를 위한 플래그 저장
        int[] timestampFlag = new int[]{-1, -1};
        bfs.offer(timestampFlag);

        int[] curPos;
        int x, y, nextX, nextY;
        int time = 0;
        while (!bfs.isEmpty()) {
            curPos = bfs.poll();

            // 1분 지남
            if (curPos == timestampFlag) {

                // 모든 썩은 오렌지에 대한 탐색이 끝난 경우 루프 종료
                if(bfs.isEmpty()) break;

                time++;

                // 싱싱한 오렌지가 0개면 바로 반환
                if (numFresh == 0) return time;

                // 다음 시간 탐색
                bfs.offer(timestampFlag);
                continue;
            }

            // 주변에 싱싱한 오렌지를 찾아서 썩게 함
            x = curPos[0];
            y = curPos[1];
            for (int[] d : directions) {
                nextX = x + d[0];
                nextY = y + d[1];

                // 범위 탐색
                if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) {
                    if (grid[nextX][nextY] == 1) {
                        numFresh--;
                        grid[nextX][nextY] = 2;
                        bfs.offer(new int[]{nextX, nextY});
                    }
                }
            }
        }


        return (numFresh == 0) ? time : -1;
    }

    public static void main(String[] args) {
        Prob994RottingOranges sol = new Prob994RottingOranges();

        int[][][] grids = {
                {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}},
                {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}},
                {{0, 2}}
        };

        int[] ans = {4, -1, 0};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.orangesRotting(grids[i]);
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
