package from301to400;

// https://leetcode.com/problems/range-sum-query-2d-mutable/
// Ref : Problem 304
// TODO : Binary Indexed Tree
public class Prob308RangeSumQuery2DMutable {
    class NumMatrix {

        // partialSum[i+1][j+1] : (0,0) ~ (i,j) 까지의 합
        private int[][] partialSum;
        private int[][] matrix;
        private int rowSize, colSize;

        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;

            rowSize = matrix.length;
            colSize = matrix[0].length;
            partialSum = new int[rowSize + 1][colSize + 1];
            for (int row = 1; row <= rowSize; row++) {
                for (int col = 1; col <= colSize; col++) {
                    partialSum[row][col] = matrix[row - 1][col - 1] + partialSum[row - 1][col] + partialSum[row][col - 1] - partialSum[row - 1][col - 1];
                }
            }
        }

        public void update(int row, int col, int val) {
            int diff = val - matrix[row][col];
            matrix[row][col] = val;

            // update partial sum : (row,col) 부터 시작하는 직사각형 영역에 대해서만
            // => (0,0) ~ (i,j) 까지의 부분합 (== partialSum[i+1][j+1]) for i>= row, j>=col
            row++;
            col++;
            for (int i = row; i <= rowSize; i++) {
                for (int j = col; j <= colSize; j++) {
                    partialSum[i][j] += diff;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return partialSum[row2 + 1][col2 + 1] - partialSum[row1][col2 + 1]
                    - partialSum[row2 + 1][col1] + partialSum[row1][col1];
        }
    }
}
