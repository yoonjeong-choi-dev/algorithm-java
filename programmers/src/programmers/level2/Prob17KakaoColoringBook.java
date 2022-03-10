package programmers.level2;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/1829
public class Prob17KakaoColoringBook {
    private boolean[][] isVisited;
    private int[][] picture;
    private int row, col;
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[] solution(int m, int n, int[][] picture) {
        row = m;
        col = n;
        isVisited = new boolean[row][col];
        this.picture = picture;

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 칠해져 있지 않거나 이미 방문한 경우 pass
                if (picture[i][j] == 0 || isVisited[i][j]) continue;

                int area = dfs(i, j);
                System.out.printf("%d, %d : %d\n", i, j, area);
                if (maxSizeOfOneArea < area) {
                    maxSizeOfOneArea = area;
                }
                numberOfArea++;
            }
        }

        return new int[]{numberOfArea, maxSizeOfOneArea};
    }

    private int dfs(int i, int j) {
        // 이미 방문한 경우
        if (isVisited[i][j]) return 0;

        // 현재 픽셀 방문
        isVisited[i][j] = true;
        int ret = 1;

        // 방문 가능한 모든 케이스 탐색
        for(int d=0;d<directions.length;d++) {
            int nextRow = i + directions[d][0];
            int nextCol = j + directions[d][1];
            if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col && picture[i][j] == picture[nextRow][nextCol]) {
                ret += dfs(nextRow, nextCol);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Prob17KakaoColoringBook sol = new Prob17KakaoColoringBook();

        int[] m = {6, 6, 13};
        int[] n = {4, 4, 16};
        int[][][] pics = {
                {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}},
                {{1, 1, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}},
                {{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 0}, {0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0}, {0, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}}
        };

        int[][] ans = {{4, 5}, {2, 6}, {12, 120}};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            int[] mySol = sol.solution(m[i], n[i], pics[i]);
            if (Arrays.equals(ans[i], mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
