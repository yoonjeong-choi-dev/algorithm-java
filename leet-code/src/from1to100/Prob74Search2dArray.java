package from1to100;

// https://leetcode.com/problems/search-a-2d-matrix/
public class Prob74Search2dArray {

    public boolean searchMatrix(int[][] matrix, int target) {
        // 각 열에 대해서 탐색해야 하므로, 열의 크기 저장
        int col = matrix[0].length -1;
        for (int[] mat : matrix) {

            // 행렬을 1차원 배열로 reshape 한 경우 오름차순
            // => target 이 있는 행은 행의 최대값 이하
            if (mat[col] == target) {
                return true;
            } else if (mat[col] > target) {
                for(int i=0;i<col;i++){
                    if(mat[i] == target) return true;
                }
                break;
            }
        }

        return false;
    }
}
