package programmers.level1;

// https://programmers.co.kr/learn/courses/30/lessons/12931
public class Prob8SumDigit {
    public int solution(int n) {
        int digitSum = 0;
        while(n>0){
            digitSum += n % 10;
            n /= 10;
        }

        return digitSum;
    }
}
