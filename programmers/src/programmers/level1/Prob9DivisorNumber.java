package programmers.level1;

import java.util.HashSet;

// https://programmers.co.kr/learn/courses/30/lessons/77884
public class Prob9DivisorNumber {
    public int solution(int left, int right) {
        // 제곱수만 약수의 개수가 홀수
        int minSqrt = (int) Math.sqrt(left);
        int maxSqrt = (int) Math.sqrt(right);
        HashSet<Integer> squarePow = new HashSet<>();
        for(int i= minSqrt; i<=maxSqrt;i++) {
            squarePow.add(i*i);
        }

        int answer = 0;
        for(int num=left;num<=right;num++) {
            if(squarePow.contains(num)) {
                answer -= num;
            } else {
                answer += num;
            }
        }
        return answer;
    }
}
