package programmers.level1;

// https://programmers.co.kr/learn/courses/30/lessons/12947
public class Prob13HarshadNumber {
    public boolean solution(int x) {
        int originX = x;
        int digitSum = 0;
        while(x>0){
            digitSum += x % 10;
            x /= 10;
        }

        return originX % digitSum == 0;
    }
}
