package from1001to1100;

// https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
public class Prob1074NumberOfSubMatricesThatSumToTarget {

    private int[][] partialSum;
    private int rowSize, colSize;

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        makePartialSum(matrix);


        int ans = 0;
        for (int x1 = 0; x1 < rowSize; x1++) {
            for (int y1 = 0; y1 < colSize; y1++) {
                for (int x2 = x1; x2 < rowSize; x2++) {
                    for (int y2 = y1; y2 < colSize; y2++) {
                        if (getPartialSum(x1, y1, x2, y2) == target) ans++;
                    }
                }
            }
        }
        return ans;
    }

    private void makePartialSum(int[][] matrix) {
        rowSize = matrix.length;
        colSize = matrix[0].length;

        // Ref : Problem 304.
        // partialSum[i+1][j+1] : (0,0) ~ (i,j) 까지의 합
        partialSum = new int[rowSize + 1][colSize + 1];
        for (int row = 1; row <= rowSize; row++) {
            for (int col = 1; col <= colSize; col++) {
                partialSum[row][col] = matrix[row - 1][col - 1] + partialSum[row - 1][col] + partialSum[row][col - 1] - partialSum[row - 1][col - 1];
            }
        }
    }

    // top-left : (x1, y1) & bottom-right : (x2, y2)
    private int getPartialSum(int x1, int y1, int x2, int y2) {
        return partialSum[x2 + 1][y2 + 1] - partialSum[x1][y2 + 1] - partialSum[x2 + 1][y1] + partialSum[x1][y1];
    }

    public static void main(String[] args) {
        Prob1074NumberOfSubMatricesThatSumToTarget sol = new Prob1074NumberOfSubMatricesThatSumToTarget();
        sol.numSubmatrixSumTarget(new int[][]{{0,1,0},{1,1,1}, {0,1,0}}, 0);
    }
}
