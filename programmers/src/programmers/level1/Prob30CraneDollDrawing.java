package programmers.level1;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/64061
public class Prob30CraneDollDrawing {
    public int solution(int[][] board, int[] moves) {
        int colSize = board.length;
        int rowSize = board[0].length;

        // board 정보를 이용하여 각 열의 정보를 행의 정보로 변환한 2차원 배열
        int[][] b = new int[rowSize][colSize];

        // boardCurIdx[i] : board[i]의 현재 인덱스(스택의 top 인덱스)
        int[] boardCurIdx = new int[rowSize];
        for (int i = 0; i < rowSize; i++) boardCurIdx[i] = -1;

        for (int i = 0; i < rowSize; i++) {
            for (int j = colSize - 1; j >= 0; j--) {
                b[i][colSize - 1 - j] = board[j][i];

                if (board[j][i] > 0) boardCurIdx[i]++;
            }
        }

        // bucketIdx : bucket의 현재 인덱스
        int[] bucket = new int[30 * 30];
        int bucketIdx = 0;

        // 계산을 위해 moves 정보를 1씩 빼줘서 인덱스를 맞춤
        for (int i = 0; i < moves.length; i++) moves[i]--;

        // 시뮬레이션 시작
        int answer = 0;
        int doll;
        for (int move : moves) {

            // 해당 열에 인형이 없는 경우 pass
            if (boardCurIdx[move] < 0) continue;

            // 해당 열에서 인형 뽑기
            doll = b[move][boardCurIdx[move]--];

            // 바구니에 저장
            if (bucketIdx > 0 && bucket[bucketIdx - 1] == doll) {
                // 바로 아래와 동일한 것이면 2개 터뜨림
                answer += 2;
                bucketIdx--;
            } else {
                // 아래와 동일하지 않으면 쌓음
                bucket[bucketIdx++] = doll;
            }

        }

        return answer;
    }

    public static void main(String[] args) {
        Prob30CraneDollDrawing sol = new Prob30CraneDollDrawing();

        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};

        System.out.println("Answer : " + 4);
        System.out.println("My Sol : " + sol.solution(board, moves));
    }
}
