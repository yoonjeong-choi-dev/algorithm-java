package from1301to1400;

import java.util.PriorityQueue;

// https://leetcode.com/problems/sort-the-matrix-diagonally/
public class Prob1329SortTheMatrixDiagonally {
    public int[][] diagonalSort(int[][] mat) {
        int rowSize = mat.length, colSize = mat[0].length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int curRange;

        // bottom-left
        for (int row = 0; row < rowSize; row++) {
            curRange = Math.min(rowSize - row, colSize);
            for (int i = 0; i < curRange; i++) {
                minHeap.add(mat[row + i][i]);
            }

            for (int i = 0; i < curRange; i++) {
                mat[row + i][i] = minHeap.poll();
            }
        }

        // top-right
        for (int col = 1; col < colSize; col++) {
            curRange = Math.min(rowSize, colSize - col);
            for (int i = 0; i < curRange; i++) {
                minHeap.add(mat[i][col + i]);
            }

            for (int i = 0; i < curRange; i++) {
                mat[i][col + i] = minHeap.poll();
            }
        }

        return mat;
    }
}
