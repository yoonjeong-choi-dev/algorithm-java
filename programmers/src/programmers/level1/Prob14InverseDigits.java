package programmers.level1;

import java.util.ArrayList;

// https://programmers.co.kr/learn/courses/30/lessons/12932
public class Prob14InverseDigits {
    public int[] solution(long n) {
        // ternary[i] : 3진법 표현 시 왼쪽 기준으로 i번째 자리수의 숫자
        ArrayList<Integer> digits = new ArrayList<>();
        while (n > 0) {
            digits.add((int) (n % 10));
            n /= 10;
        }

        int numDigits = digits.size();
        int[] answer = new int[numDigits];
        for(int i=0; i<numDigits;i++) {
            answer[i] = digits.get(i);
        }

        return answer;
    }
}
