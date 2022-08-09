package from301to400;

// https://leetcode.com/problems/sparse-matrix-multiplication/
public class Prob311SparseMatrixMultiplication {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        // (M, K) * (K, N) => (M, N)
        int M = mat1.length, K = mat1[0].length, N = mat2[0].length;

        int[][] ans = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < K; k++) {
                    if (mat1[i][k] != 0 && mat2[k][j] != 0) ans[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }

        return ans;
    }
}
