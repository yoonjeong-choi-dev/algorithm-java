package topcoder.ch5_bruteforce;

import java.util.ArrayDeque;
import java.util.Queue;

public class MazeMaker {

    private int rowSize, colSize;

    public int longestPath(String[] maze, int startRow, int startCol, int[] moveRow, int[] moveCol) {
        rowSize = maze.length;
        colSize = maze[0].length();
        int numDirections = moveRow.length;

        // dist[i][j] : (i,j)로 가는 최단 거리
        // 처음에는 -1로 초기화
        int[][] dist = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                dist[i][j] = -1;
            }
        }

        dist[startRow][startCol] = 0;

        // bfs : 0 - x 좌표, 1 - y 좌표
        Queue<int[]> bfs = new ArrayDeque<>(rowSize * colSize);
        bfs.add(new int[]{startRow, startCol});

        // bfs 탐색을 통해 각 정점의 최단거리 계산
        int[] curPos;
        int nextX, nextY;
        while (!bfs.isEmpty()) {
            curPos = bfs.poll();

            // 현재 정점에서 이동 가능한 곳들 계산
            for (int i = 0; i < numDirections; i++) {
                nextX = curPos[0] + moveRow[i];
                nextY = curPos[1] + moveCol[i];

                //System.out.printf("x : %d, y: %d - %s\n", nextX, nextY, inBoundary(nextX,nextY));

                // 미로 밖으로 나가는 경우 pass
                if (!inBoundary(nextX, nextY)) continue;

                // 이동 불가능한 지점인 경우 pass
                if (maze[nextX].charAt(nextY) == 'X') continue;

                // 방문하지 않은 경우에만 다음 지점으로 이동
                // bfs => 처음 방문한 경우가 최단 거리
                if (dist[nextX][nextY] == -1) {
                    dist[nextX][nextY] = dist[curPos[0]][curPos[1]] + 1;
                    bfs.add(new int[]{nextX, nextY});
                }
            }
        }

        // 최대 거리 찾기
        int answer = -1;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                // 방문 불가능한 정점이 존재하면 -1
                if(dist[i][j] == -1 && maze[i].charAt(j) == '.') return -1;

                // 방문 가능한 정점들 중 거리가 가장 긴 것들 계산
                if(dist[i][j] > 0 && answer < dist[i][j]) {
                    answer = dist[i][j];
                }
            }
        }


        return answer;
    }

    private boolean inBoundary(int x, int y) {
        if (x < 0 || x >= rowSize) return false;
        if (y < 0 || y >= colSize) return false;
        return true;
    }

    public static void main(String[] args) {
        MazeMaker sol = new MazeMaker();

        String[][] maze = {
                {"...", "...", "..."},
                {"...", "...", "..."},
                {"X.X", "...", "XXX", "X.X"},
                {".......", "X.X.X..", "XXX...X", "....X..", "X....X.", "......."},
                {"......."},
                {"..X.X.X.X.X.X."}
        };
        int[] rows = {0, 0, 0, 5, 0, 0};
        int[] cols = {1, 1, 1, 0, 0, 0};
        int[][] moveRows = {
                {1, 0, -1, 0},
                {1, 0, -1, 0, 1, 1, -1, -1},
                {1, 0, -1, 0},
                {1, 0, -1, 0, -2, 1},
                {1, 0, 1, 0, 1, 0},
                {2, 0, -2, 0}
        };
        int[][] moveCols = {
                {0, 1, 0, -1},
                {0, 1, 0, -1, 1, -1, 1, -1},
                {0, 1, 0, -1},
                {0, -1, 0, 1, 3, 0},
                {0, 1, 0, 1, 0, 1},
                {0, 2, 0, -2}
        };

        int[] ans = {3, 2, -1, 7, 6, -1};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.longestPath(maze[i], rows[i], cols[i], moveRows[i], moveCols[i]);
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
