package from301to400;

import java.util.Arrays;
import java.util.TreeSet;

// https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
public class Prob363MaxSumOfRectangleNoLargerThanK {

    private int ans, target;
    private int[] partialSum;

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rowSize = matrix.length, colSize = matrix[0].length;
        ans = Integer.MIN_VALUE;
        target = k;

        // matrix[start:i] 부분 행렬에 대한 부분 합
        // partialSum[j] = matrix[start:i][0:j] 부분 합
        partialSum = new int[colSize];

        // matrix[startRow:] 에 대한 부분 문제
        for (int startRow = 0; startRow < rowSize; startRow++) {
            Arrays.fill(partialSum, 0);

            for (int row = startRow; row < rowSize; row++) {
                // matrix[startRow:row] 부분 행렬에 대해서 탐색
                for (int col = 0; col < colSize; col++) partialSum[col] += matrix[row][col];

                // matrix[startRow:row] 에서 정답 찾기
                // <=> Find i<j s.t partialSum[j] - partialSum[i] <= k
                findTarget();

                // 정답은 target 보다 클 수 없음 => 조기 종료
                if (ans == target) return ans;
            }
        }

        return ans;
    }

    private void findTarget() {
        // 빠른 탐색을 위해 BST 사용
        TreeSet<Integer> sortedPartialSum = new TreeSet<>();

        // 단일 요소일 수도 있으므로 0 추가
        sortedPartialSum.add(0);

        int curSum = 0;
        for (int num : partialSum) {
            curSum += num;

            // Find i<j s.t partialSum[j] - partialSum[i] <= k
            // <=> Find smallest x in sortedPartialSum s.t sum - x <= k i.e sum-k <= x
            Integer x = sortedPartialSum.ceiling(curSum - target);

            if (x != null) ans = Math.max(ans, curSum - x);

            sortedPartialSum.add(curSum);
        }
    }
}
