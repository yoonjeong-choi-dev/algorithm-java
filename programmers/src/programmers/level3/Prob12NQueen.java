package programmers.level3;

import java.util.ArrayList;
import java.util.List;

// https://programmers.co.kr/learn/courses/30/lessons/12952
public class Prob12NQueen {
    private int boardSize;

    public int solution(int n) {
        boardSize = n;

        // info[i] : i번째 줄에 있는 퀸의 열 위치
        List<Integer> info = new ArrayList<>(boardSize);

        return recursive(0, info);
    }

    private int recursive(int curRow, List<Integer> rowInfo) {
        if (curRow == boardSize) {
            return 1;
        }

        int ans = 0;

        // 현재 줄에 배치 가능한 위치에 배치
        boolean canAssign;
        int curCol;
        for (int i = 0; i < boardSize; i++) {
            canAssign = true;

            // [0, curRow-1] 까지 정보 확인
            for (int j = 0; j < curRow; j++) {
                curCol = rowInfo.get(j);

                // 같은 열이면 불가능
                if (i == curCol) {
                    canAssign = false;
                    break;
                }

                // 대각선 열 불가능
                if (i == curCol - (j - curRow) || i == curCol + (j - curRow)) {
                    canAssign = false;
                    break;
                }
            }

            if (canAssign) {
                rowInfo.add(i);
                ans += recursive(curRow + 1, rowInfo);
                rowInfo.remove(curRow);
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        Prob12NQueen sol = new Prob12NQueen();

        // i = 1 : 1
        // i = 2 : 0
        // i = 3 : 0
        // i = 4 : 2
        for (int i = 1; i <= 12; i++) {
            int ans = sol.solution(i);
            System.out.printf("%d : %d\n", i, ans);
        }
    }
}
