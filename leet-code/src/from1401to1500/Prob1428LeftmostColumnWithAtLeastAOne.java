package from1401to1500;

import java.util.List;

// https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
public class Prob1428LeftmostColumnWithAtLeastAOne {
    interface BinaryMatrix {
        int get(int row, int col);

        List<Integer> dimensions();
    }

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rowSize = binaryMatrix.dimensions().get(0);
        int colSize = binaryMatrix.dimensions().get(1);

        int ans = colSize;

        int left, right, mid, curVal;
        for (int i = 0; i < rowSize; i++) {
            left = 0;
            right = colSize - 1;
            while (left <= right) {
                mid = left + (right - left) / 2;
                curVal = binaryMatrix.get(i, mid);
                if (curVal == 1) {
                    ans = Math.min(ans, mid);
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return ans == colSize ? -1 : ans;
    }

    private int diagonalSearch(BinaryMatrix binaryMatrix) {
        int rowSize = binaryMatrix.dimensions().get(0);
        int colSize = binaryMatrix.dimensions().get(1);

        int curRow = 0, curCol = colSize - 1;

        while (curRow < rowSize && curCol >= 0) {
            if (binaryMatrix.get(curRow, curCol) == 0) {
                // each row of the matrix is sorted in non-decreasing order
                // => 오른쪽부분은 모두 0 => 다음 행 조사
                curRow++;
            } else {
                // curCol 이 현재 정답 => 최소값을 찾기 위해 이전 열 조사
                curCol--;
            }
        }

        return curCol == colSize - 1 ? -1 : curCol + 1;
    }
}
