package from1901to2000;


// https://leetcode.com/problems/find-a-peak-element-ii/
public class Prob1901FindPeakElement2 {
    public int[] findPeakGrid(int[][] mat) {
        int rowSize = mat.length, colSize = mat[0].length;

        if (colSize == 1) {
            int maxIdx = 1;
            for (int i = 1; i < rowSize; i++) {
                if (mat[i][0] > mat[maxIdx][0]) maxIdx = i;
            }

            return new int[]{maxIdx, 0};
        }

        int left = 0, right = colSize - 1;
        int mid;
        int[] maxValues = new int[3];
        int[] maxIndex = new int[3];
        int maxVal;
        while (left <= right) {
            mid = (left + right) / 2;

            // mid 및 양 옆의 열에서 가장 큰 값을 탐색
            maxVal = 0;
            int i = 0;
            for (int col = mid - 1; col <= mid + 1; col++) {

                maxValues[i] = -1;
                maxIndex[i] = 0;

                // 내부인 경우에만 업데이트
                if (col >= 0 && col < colSize) {
                    for (int row = rowSize - 1; row >= 0; row--) {
                        if (maxValues[i] < mat[row][col]) {
                            maxValues[i] = mat[row][col];
                            maxIndex[i] = row;
                            maxVal = Math.max(maxVal, maxValues[i]);
                        }
                    }
                }
                i++;
            }

            if (maxValues[1] == maxVal) {
                // 양 옆의 열보다 가운데 열이 더 크므로 peak
                return new int[]{maxIndex[1], mid};
            } else if (maxValues[0] == maxVal) {
                // 왼쪽에 정답 존재
                right = mid - 1;
            } else {
                // 오른쪽에 정답 존재
                left = mid + 1;
            }
        }

        // 가장자리가 모두 음수이고, 내부는 양수여서 여기에 코드가 오지 못함
        return null;
    }
}
