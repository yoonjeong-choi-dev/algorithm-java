package from1to100;

// https://leetcode.com/problems/rotate-image/
public class Prob48RotateImage {
    public void rotate(int[][] matrix) {
        myFourLoopSolution(matrix);
    }

    // Ref : Problem 59
    private void myFourLoopSolution(int[][] matrix) {
        int start = 0, end, prev;

        int curSize = matrix.length;
        while (curSize > 1) {

            end = start + curSize - 1;
            for (int i = 0; i < curSize - 1; i++) {
                // top -> left -> bottom -> right 순서로 값 이동
                prev = matrix[start][start + i];
                matrix[start][start + i] = matrix[end - i][start];
                matrix[end - i][start] = matrix[end][end - i];
                matrix[end][end - i] = matrix[start + i][end];
                matrix[start + i][end] = prev;
            }

            start++;
            curSize -= 2;
        }
    }

    // Use Math
    // Rotate (x,y) with degree 90 clockwise : (x,y) -> (y,-x) -> (-x,-y) -> (-y, x)
    // <=> reflection with y=x then reflection with x-axis : (x,y) -> (y,x) -> (y, -x)
    // <=> reflection with y=-x then reflection with y-axis : (x,y) -> (-y,-x) -> (y, -x)
    private void solutionWithMatrixOperation(int[][] matrix) {
        int len = matrix.length;
        int mid = len / 2;
        int temp;

        // reflection with y=-x : transpose
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // reflection with y-axis : reflection with center column
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < mid; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][len - 1 - j];
                matrix[i][len - 1 - j] = temp;
            }
        }
    }
}
