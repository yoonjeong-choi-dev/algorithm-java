package from401to500;

// https://leetcode.com/problems/diagonal-traverse/
public class Prob498DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] mat) {
        int rowSize = mat.length, colSize = mat[0].length;
        int[] ans = new int[rowSize * colSize];

        int[] temp = new int[Math.min(rowSize, colSize)];
        int ansIdx = 0;
        int testNum, x, y;
        boolean reverse = true;

        // left-top
        for (int i = 0; i < colSize; i++) {
            x = 0;
            y = i;
            testNum = Math.min(rowSize, i + 1);
            for (int j = 0; j < testNum; j++) {
                temp[j] = mat[x++][y--];
            }

            if (reverse) {
                for (int j = testNum - 1; j >= 0; j--) ans[ansIdx++] = temp[j];
            } else {
                for (int j = 0; j < testNum; j++) ans[ansIdx++] = temp[j];
            }

            reverse = !reverse;
        }

        // right-bottom
        int colStart = colSize - 1;
        for (int i = 1; i < rowSize; i++) {
            x = i;
            y = colStart;
            testNum = Math.min(rowSize-i, colSize);

            for (int j = 0; j < testNum; j++) {
                temp[j] = mat[x++][y--];
            }

            if (reverse) {
                for (int j = testNum - 1; j >= 0; j--) ans[ansIdx++] = temp[j];
            } else {
                for (int j = 0; j < testNum; j++) ans[ansIdx++] = temp[j];
            }

            reverse = !reverse;
        }


        return ans;
    }
}
