package programmers.level1;

import java.util.ArrayList;
import java.util.Collections;

// https://programmers.co.kr/learn/courses/30/lessons/12933
public class Prob15SortDigits {
    long solution(long n) {
        ArrayList<Integer> digits = new ArrayList<>();
        while (n > 0) {
            digits.add((int) (n % 10));
            n /= 10;
        }

        Collections.sort(digits);

        int numDigits = digits.size();
        long curPow = 1;
        long answer = 0;
        for (int i = 0; i < numDigits; i++) {
            answer += curPow * (long) digits.get(i);
            curPow *= 10;
        }

        return answer;
    }
}
