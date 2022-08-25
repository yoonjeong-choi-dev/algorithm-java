package from701to800;

// https://leetcode.com/problems/toeplitz-matrix/
public class Prob766ToeplitzMatrix {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int rowSize = matrix.length, colSize = matrix[0].length;

        int testNum, row, col, val;

        // left-bottom side
        for (int i = 0; i < rowSize; i++) {
            row = i;
            col = 0;
            val = matrix[row][col];
            testNum = Math.min(rowSize - i, colSize);
            for (int j = 0; j < testNum; j++) {
                if (matrix[row++][col++] != val) return false;
            }
        }

        // right-top side
        for (int i = 1; i < colSize; i++) {
            row = 0;
            col = i;
            val = matrix[row][col];
            testNum = Math.min(rowSize, colSize - i);
            for (int j = 0; j < testNum; j++) {
                if (matrix[row++][col++] != val) return false;
            }
        }

        return true;
    }
}
