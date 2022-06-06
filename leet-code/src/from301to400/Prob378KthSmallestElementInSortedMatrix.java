package from301to400;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
public class Prob378KthSmallestElementInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int matSize = matrix.length;

        PriorityQueue<int[]> rowPq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(matrix[o1[0]][o1[1]], matrix[o2[0]][o2[1]]);
            }
        });

        for (int i = 0; i < matSize; i++) {
            rowPq.add(new int[]{i, 0});
        }

        int[] curRow ;
        for (int i = 0; i < k - 1; i++) {
            curRow = rowPq.poll();
            curRow[1]++;

            if (curRow[1] != matSize) {
                rowPq.add(curRow);
            }
        }
        curRow = rowPq.poll();
        return matrix[curRow[0]][curRow[1]];
    }
}
