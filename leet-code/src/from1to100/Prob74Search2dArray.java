package from1to100;

// https://leetcode.com/problems/search-a-2d-matrix/
public class Prob74Search2dArray {

    public boolean searchMatrix(int[][] matrix, int target) {
        // 각 열에 대해서 탐색해야 하므로, 열의 크기 저장
        int col = matrix[0].length - 1;
        for (int[] mat : matrix) {

            // 행렬을 1차원 배열로 reshape 한 경우 오름차순
            // => target 이 있는 행은 행의 최대값 이하
            if (mat[col] == target) {
                return true;
            } else if (mat[col] > target) {
                for (int i = 0; i < col; i++) {
                    if (mat[i] == target) return true;
                }
                break;
            }
        }

        return false;
    }

    // Improved : 오름차순으로 정렬된 행렬이므로 이분 탐색 이용
    private boolean binarySearch(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;

        // 행렬을 1차원으로 flatten 하여 생각
        int left = 0, right = row * col - 1;
        int mid, midVal;
        while (left <= right) {
            mid = (left + right) / 2;
            midVal = matrix[mid / col][mid % col];
            if (midVal == target) return true;
            else if (midVal > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
