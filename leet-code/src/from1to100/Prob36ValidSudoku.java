package from1to100;

// https://leetcode.com/problems/valid-sudoku/
public class Prob36ValidSudoku {

    static final int size = 9;

    // TODO : 테스트 입력값이 1~9 이므로 비트 연산 이용
    public boolean isValidSudoku(char[][] board) {

        // 스도쿠의 조건 3개에 대한 정보
        // 행, 열, 박스 기준 특정 숫자가 발생한 비트가 1
        // => 각 기준에 대한 비트 저장 : 2^9 = 516 => int 형 저장 가능
        int[] rowSide = new int[size];
        int[] colSide = new int[size];
        int[] boxSide = new int[size];

        // 현재 탐색하는 숫자 및 탐색하는 숫자의 비트 계산
        int curValue, curBitPosition;
        int curBoxIdx;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // 비어 있는 경우 무시
                if (board[i][j] == '.') continue;

                curValue = board[i][j] - '1';
                curBitPosition = 1 << curValue;

                // Rule 1 : 현재 행에 중복되는 숫자가 있는 경우에는 거짓 반환. 중복되지 않은 경우 비트 데이터 업데이트
                if ((rowSide[i] & curBitPosition) > 0) return false;
                else rowSide[i] |= curBitPosition;

                // Rule 2 : 현재 열에 중복되는 숫자가 있는 경우에는 거짓 반환. 중복되지 않은 경우 비트 데이터 업데이트
                if ((colSide[j] & curBitPosition) > 0) return false;
                else colSide[j] |= curBitPosition;

                // Rule 3 : 현재 박스에 중복되는 숫자가 있는 경우에는 거짓 반환. 중복되지 않은 경우 비트 데이터 업데이트
                curBoxIdx = (i / 3) * 3 + j / 3;
                if((boxSide[curBoxIdx] & curBitPosition) > 0) return false;
                else boxSide[curBoxIdx] |= curBitPosition;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Prob36ValidSudoku sol = new Prob36ValidSudoku();

        char[][][] boards = {
                {
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}},
                {
                        {'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}
        };

        boolean[] ans = {true, false};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            boolean mySol = sol.isValidSudoku(boards[i]);
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
