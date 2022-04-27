package from301to400;

// https://leetcode.com/problems/range-sum-query-2d-immutable/
public class Prob304RangeSumQuery2D {
    class NumMatrix {
        // partialSum[i+1][j+1] : (0,0) ~ (i,j) 까지의 합
        private int[][] partialSum;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            partialSum = new int[m + 1][n + 1];
            for (int row = 1; row <= m; row++) {
                for (int col = 1; col <= n; col++) {
                    partialSum[row][col] =  matrix[row - 1][col - 1] + partialSum[row-1][col] + partialSum[row][col-1] - partialSum[row-1][col-1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return partialSum[row2 + 1][col2 + 1] - partialSum[row1][col2 + 1]
                    - partialSum[row2 + 1][col1] + partialSum[row1][col1];
        }
    }

}
