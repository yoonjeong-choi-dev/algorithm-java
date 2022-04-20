package from1801to1900;

// https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/
public class Prob1886DetermineWhetherMatrixIsRotated {
    public boolean findRotation(int[][] mat, int[][] target) {
        int size = mat.length, curRow, curCol;
        boolean curAns;

        // Rotate 0 Test
        curAns = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mat[i][j] != target[i][j]) {
                    curAns = false;
                    break;
                }
            }
            if (!curAns) break;
        }
        if (curAns) return true;

        // Rotate 90 Test
        curAns = true;
        curCol = size - 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mat[i][j] != target[j][curCol]) {
                    curAns = false;
                    break;
                }
                ;
            }
            if (!curAns) break;
            curCol--;
        }
        if (curAns) return true;

        // Rotate -90 Test
        curAns = true;
        curCol = size - 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (target[i][j] != mat[j][curCol]) {
                    curAns = false;
                    break;
                }
                ;
            }
            if (!curAns) break;
            curCol--;
        }
        if (curAns) return true;

        // Rotate 180 Test
        curAns = true;
        curRow = size - 1;
        for (int i = 0; i < size; i++) {
            curCol = size - 1;
            for (int j = 0; j < size; j++) {
                if (mat[i][j] != target[curRow][curCol--]) {
                    curAns = false;
                    break;
                }
            }
            if (!curAns) break;
            curRow--;
        }

        return curAns;
    }
}
