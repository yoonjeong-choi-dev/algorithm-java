package programmers.level2;

// https://programmers.co.kr/learn/courses/30/lessons/12905
public class Prob25MaxSquare {
    public int solution(int[][] board) {
        int row = board.length, col = board[0].length;

        // score[i][j] : 좌상단이 (i,j)일 때 가장 큰 정사각형의 길이
        int[][] scores = new int[row][col];
        int answer = 0;

        // 계산 편의를 위하 오른쪽 및 아래쪽 초기화
        // 1이 있는 경우, 최대값은 1로 업데이트
        for(int i=0;i<row;i++){
            scores[i][col-1] = board[i][col-1];
            answer = Math.max(board[i][col-1], answer);
        }
        for(int j=0;j<col;j++){
            scores[row-1][j] = board[row-1][j];
            answer = Math.max(board[row-1][j], answer);
        }

        // 좌하단부터 시작
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                if (board[i][j] == 0) {
                    scores[i][j] = 0;
                    continue;
                }

                // 오른쪽 및 아래쪽 위치에 있는 가장 큰 정사각형 계산
                int maxLen = scores[i+1][j]+1;
                maxLen = Math.min(maxLen, scores[i][j+1]+1);
                maxLen = Math.min(maxLen, scores[i + 1][j + 1] + 1);
                answer = Math.max(maxLen, answer);
                scores[i][j] = maxLen;
            }
        }


        return answer * answer;
    }

    public static void main(String[] args) {
        Prob25MaxSquare sol = new Prob25MaxSquare();
        int[][][] boards = {
                {{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}},
                {{0, 0, 1, 1}, {1, 1, 1, 1}}
        };
        int[] ans = {9, 4};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================= Problem %d\n", i);
            int mySol = sol.solution(boards[i]);
            if (ans[i] == mySol) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
