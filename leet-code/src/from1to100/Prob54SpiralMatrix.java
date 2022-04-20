package from1to100;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/spiral-matrix/
public class Prob54SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>(matrix.length * matrix[0].length);

        int rowStart = 0, rowEnd = matrix.length - 1, colStart = 0, colEnd = matrix[0].length - 1;
        while (rowStart < rowEnd && colStart < colEnd) {

            // top -> left -> bottom -> right
            for (int i = colStart; i < colEnd; i++) ans.add(matrix[rowStart][i]);
            for (int i = rowStart; i < rowEnd; i++) ans.add(matrix[i][colEnd]);
            for (int i = colEnd; i > colStart; i--) ans.add(matrix[rowEnd][i]);
            for (int i = rowEnd; i > rowStart; i--) ans.add(matrix[i][colStart]);


            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
        }

        if (rowStart == rowEnd) {
            for (int i = colStart; i <= colEnd; i++) ans.add(matrix[rowStart][i]);
        } else if (colStart == colEnd) {
            for (int i = rowStart; i <= rowEnd; i++) ans.add(matrix[i][colStart]);
        }

        return ans;
    }

    public static void main(String[] args) {
        Prob54SpiralMatrix sol = new Prob54SpiralMatrix();
        sol.spiralOrder(new int[][]{{3}, {2}});
    }
}
