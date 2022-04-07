package from501to600;

import java.util.Arrays;

// https://leetcode.com/problems/01-matrix/
public class Prob542BinaryMatrix {
    public int[][] updateMatrix(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;

        int[][] dist = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 0) dist[i][j] = 0;
                else dist[i][j] = Integer.MAX_VALUE;
            }
        }

        // 우하향으로 탐색
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 우하향 노드가 이미 거리 계산이 왼료된 경우 거리 업데이트
                if (i > 0 && dist[i - 1][j] != Integer.MAX_VALUE) dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                if (j > 0 && dist[i][j - 1] != Integer.MAX_VALUE) dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
            }
        }

        // 좌상향으로 탐색
        for(int i=row-1;i>=0;i--){
            for(int j=col-1;j>=0;j--) {
                // 좌상향 노드가 이미 거리 계산이 왼료된 경우 거리 업데이트
                if (i < row-1 && dist[i + 1][j] != Integer.MAX_VALUE) dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                if (j < col-1 && dist[i][j + 1] != Integer.MAX_VALUE) dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Prob542BinaryMatrix sol = new Prob542BinaryMatrix();

        int[][][] matrices = {
                {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}},
                {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}},
                {{1, 0, 1, 1, 0, 0, 1, 0, 0, 1}, {0, 1, 1, 0, 1, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 0, 0, 1, 0, 0}, {1, 0, 1, 0, 1, 1, 1, 1, 1, 1}, {0, 1, 0, 1, 1, 0, 0, 0, 0, 1}, {0, 0, 1, 0, 1, 1, 1, 0, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 0, 1, 1}, {1, 0, 0, 0, 1, 1, 1, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0, 1, 0}, {1, 1, 1, 1, 0, 1, 0, 0, 1, 1}}
        };

        int[][][] ans = {
                {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}},
                {{0, 0, 0}, {0, 1, 0}, {1, 2, 1}},
                {{1, 0, 1, 1, 0, 0, 1, 0, 0, 1}, {0, 1, 1, 0, 1, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 0, 0, 1, 0, 0}, {1, 0, 1, 0, 1, 1, 1, 1, 1, 1}, {0, 1, 0, 1, 1, 0, 0, 0, 0, 1}, {0, 0, 1, 0, 1, 1, 1, 0, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 0, 1, 1}, {1, 0, 0, 0, 1, 2, 1, 1, 0, 1}, {2, 1, 1, 1, 1, 2, 1, 0, 1, 0}, {3, 2, 2, 1, 0, 1, 0, 0, 1, 1}}
        };
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            int[][] mySol = sol.updateMatrix(matrices[i]);
            if (Arrays.deepEquals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.deepToString(mySol));
                System.out.println("Expected : " + Arrays.deepToString(ans[i]));
            }
        }
    }
}
