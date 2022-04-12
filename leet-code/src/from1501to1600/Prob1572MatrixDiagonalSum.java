package from1501to1600;

// https://leetcode.com/problems/matrix-diagonal-sum/
public class Prob1572MatrixDiagonalSum {
    public int diagonalSum(int[][] mat) {
        int size = mat.length;

        int sum = 0;
        int secondaryColIdx = size - 1;
        for (int i = 0; i < size; i++) {
            // primary diagonal
            sum += mat[i][i];

            // secondary diagonal
            sum += mat[i][secondaryColIdx - i];
        }

        // 크기가 홀수인 경우 중복으로 저장된 가운데 요소를 빼준다
        if (size % 2 == 1) {
            sum -= mat[size / 2][size / 2];
        }

        return sum;
    }
}
