package from501to600;

import java.util.Arrays;

// https://leetcode.com/problems/reshape-the-matrix/
public class Prob566ReshapeMatrix {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length, n = mat[0].length;

        // 변환 불가능한 경우 원본 행렬 반환
        if (m * n != r * c) return mat;

        int[][] ans = new int[r][c];

        for (int i = 0; i < r * c; i++) {
            ans[i / c][i % c] = mat[i / n][i % n];
        }

        return ans;
    }

    public static void main(String[] args) {

        Prob566ReshapeMatrix sol = new Prob566ReshapeMatrix();

        int[][][] matrices = {{{1, 2}, {3, 4}}, {{1, 2}, {3, 4}}};
        int[] rows = {1, 2};
        int[] cols = {4, 4};

        int[][][] ans = {{{1, 2, 3, 4}}, {{1, 2}, {3, 4}}};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            int[][] mySol = sol.matrixReshape(matrices[i], rows[i], cols[i]);
            if (Arrays.deepEquals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.deepToString(mySol));
                System.out.println("Expected : " + Arrays.deepToString(ans[i]));
            }
        }
    }
}
