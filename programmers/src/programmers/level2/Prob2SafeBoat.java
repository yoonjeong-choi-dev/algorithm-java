package programmers.level2;
import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/42885
public class Prob2SafeBoat {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int answer = 0;

        // 탐욕법 : 남아있는 사람들 중 가장 가벼운 사람과 무거운 사람을 짝지어서 보냄
        int minIdx = 0, maxIdx = people.length-1;
        while(minIdx < maxIdx) {
            if(people[minIdx] + people[maxIdx] <= limit) {
                minIdx++;
                maxIdx--;
            } else {
                maxIdx--;
            }
            answer++;
        }
        if (minIdx == maxIdx) answer++;
        return answer;
    }

    public static void main(String[] args) {
        Prob2SafeBoat sol = new Prob2SafeBoat();

        System.out.println(sol.solution(new int[] {70,50,80,50}, 100));
        System.out.println(sol.solution(new int[] {70,80,50}, 100));
    }
}
