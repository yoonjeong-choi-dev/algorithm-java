package from1to100;

// https://leetcode.com/problems/set-matrix-zeroes/
public class Prob73SetMatrixZero {
    // Constraint 1 : You must do it in place
    // Constraint 2 : Constant Space Solution
    // set its entire row and column to 0's
    public void setZeroes(int[][] matrix) {
        int rowSize = matrix.length, colSize = matrix[0].length;

        boolean upperRowZero = false, leftColZero = false;
        for (int j = 0; j < colSize; j++) {
            if (matrix[0][j] == 0) {
                upperRowZero = true;
                break;
            }
        }

        // 0인 위치의 열과 행의 정보를 상단/우측 셀에 저장
        for (int i = 0; i < rowSize; i++) {
            if (matrix[i][0] == 0) leftColZero = true;

            for (int j = 1; j < colSize; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 상단행 및 우측 열을 제외하고 업데이트
        for (int i = 1; i < rowSize; i++) {
            for (int j = 1; j < colSize; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
            }
        }

        // 상단행 업데이트 : 상단 행에 0이 존재하는 경우
        if (upperRowZero) {
            for (int j = 0; j < colSize; j++) matrix[0][j] = 0;
        }

        // 우측열 업데이트 : 우측 열에 0이 존재하는 경우
        if (leftColZero) {
            for (int i = 0; i < rowSize; i++) matrix[i][0] = 0;
        }
    }

    private void additionalMemorySolution(int[][] matrix) {
        int rowSize = matrix.length, colSize = matrix[0].length;

        boolean[] rowFlag = new boolean[rowSize];
        boolean[] colFlag = new boolean[colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (matrix[i][j] == 0) {
                    rowFlag[i] = true;
                    colFlag[j] = true;
                }
            }
        }

        for (int i = 0; i < rowSize; i++) {
            if (rowFlag[i]) {
                for (int j = 0; j < colSize; j++) matrix[i][j] = 0;
            }
        }

        for (int j = 0; j < colSize; j++) {
            if (colFlag[j]) {
                for (int i = 0; i < rowSize; i++) matrix[i][j] = 0;
            }
        }
    }
}
