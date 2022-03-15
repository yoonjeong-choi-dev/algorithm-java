package programmers.level2;

import java.util.ArrayList;

// https://programmers.co.kr/learn/courses/30/lessons/12899
public class Prob35OneTwoFour {
    // 인덱스 0은 사용 X : 계산 편의를 위해서 빈 문자 등록
    static private final char[] digits = {' ', '1', '2', '4'};

    public String solution(int n) {
        // 우선 3진수로 표현 : 최대 500000000 이므로 최대 자리수는 log_3(500000000) = 14.04 < 15
        int number = n;
        ArrayList<Integer> base3List = new ArrayList<>(15);
        while (number > 0) {
            base3List.add(number % 3);
            number /= 3;
        }

        // 빠른 계산을 위해서 배열로 변경
        int numDigit = base3List.size();
        int curIdx = 0;
        int[] base3 = new int[numDigit];
        for (int d : base3List) {
            base3[curIdx++] = d;
        }

        // 문제가 되는 부분은 자리수가 0인 경우
        // 자리수가 0인 경우, 상위 비트에서 비트를 빌려와서 출력해야 함
        // 이때 빌려옴으로써 현재 비트가 -1인 경우가 있어 음수인 경우에 상위 비트에서 빌려와야함
        // 마지막 자리수(MSB)는 비트를 빌려와서 0이 될 수 있으므로 따로 계산
        for (int i = 0; i < curIdx - 1; i++) {
            // 0 이하인 상위 비트를 빌려 온다
            if (base3[i] <= 0) {
                base3[i] += 3;
                base3[i + 1] -= 1;
            }
        }

        // MSB가 0인 경우에는 해당 비트는 무시
        if (base3[curIdx - 1] == 0) {
            numDigit--;
        }

        StringBuilder answer = new StringBuilder(numDigit);
        for (int i = numDigit - 1; i >= 0; i--) {
            answer.append(digits[base3[i]]);
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        Prob35OneTwoFour sol = new Prob35OneTwoFour();

        // 1 ~ 100 까지 출력
        for (int i = 1; i <= 100; i++) {
            System.out.printf("%d : %s\n", i, sol.solution(i));
        }
    }
}
