package from201to300;

// https://leetcode.com/problems/search-a-2d-matrix-ii/
// TODO : 여러 가지 방식으로 다시 풀어보기
public class Prob240Search2DMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        //return diagonalSearch(matrix, target);
        return rectangleSearch(matrix, target);
    }

    // Solution 1 : O(row + col)
    // (why) 모든 행과 열이 오름차순으로 정렬 => 최대 아래로 row, 왼쪽으로 col 만큼만 이동
    private boolean diagonalSearch(int[][] matrix, int target) {
        int rowSize = matrix.length, colSize = matrix[0].length;
        int row = 0, col = colSize - 1;

        while (row < rowSize && col >= 0) {
            // matrix[row:][:col] 에 대해서 탐색
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) {
                // 현재 위치가 타겟보다 작은 경우 => 값을 키워야 함 => 행 위치를 아래쪽으로 이동
                row++;
            } else {
                // 현재 위치가 타겟보다 큰 경우 => 값을 줄여야 함 => 열 위치를 왼쪽으로 이동
                col--;
            }
        }

        // 모두 탐색한 경우
        return false;
    }

    // Solution 2 :
    private int[][] matrix;
    private int target;

    private boolean rectangleSearch(int[][] matrix, int target) {
        this.matrix = matrix;
        this.target = target;

        return searchSubMatrix(0, matrix.length - 1, 0, matrix[0].length - 1);
    }

    private boolean searchSubMatrix(int rowStart, int rowEnd, int colStart, int colEnd) {

        // 범위를 벗어난 경우 거짓
        if (rowStart > rowEnd || colStart > colEnd) return false;

        // 현재 부분 행렬의 최소값 및 최대값 범위 밖에 있는 경우 거짓
        if (matrix[rowStart][colStart] > target || matrix[rowEnd][colEnd] < target) return false;

        // 현재 행렬을 좌우로 분할
        // 모든 열은 오름차순으로 정렬 => 우상단 및 좌하단에 대해서만 탐색하면 됨
        int rowMid = (rowStart + rowEnd) / 2;
        int curCol = colEnd;
        while (curCol >= 0 && matrix[rowMid][curCol] >= target) {
            if (matrix[rowMid][curCol] == target) return true;
            curCol--;
        }

        // 우상단 및 좌하단에 대해서 재귀호출
        return searchSubMatrix(rowStart, rowMid, curCol+1, colEnd) ||
                searchSubMatrix(rowMid + 1, rowEnd, colStart, curCol);
    }


    public static void main(String[] args) {
        Prob240Search2DMatrix2 sol = new Prob240Search2DMatrix2();

        int[][][] mat = {
                {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}},
                {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}},
                {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}},
                {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}
        };
        int[] targets = {5, 20, 12, 26};

        boolean[] ans = {true, false, true, true};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            boolean mySol = sol.searchMatrix(mat[i], targets[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
