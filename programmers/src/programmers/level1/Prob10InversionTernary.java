package programmers.level1;

import java.util.ArrayList;

// https://programmers.co.kr/learn/courses/30/lessons/68935
public class Prob10InversionTernary {
    public int solution(int n) {
        // ternary[i] : 3진법 표현 시 왼쪽 기준으로 i번째 자리수의 숫자
        ArrayList<Integer> ternary = new ArrayList<>();
        while(n > 0) {
            ternary.add(n%3);
            n /= 3;
        }

        int len = ternary.size();
        int answer = 0;
        int pow = 1;

        for(int i=0;i<len;i++) {
            answer += pow * ternary.get(len-i-1);
            pow *= 3;
        }

        return answer;
    }
}
