package programmers.level2;

import java.util.ArrayDeque;
import java.util.Deque;

// https://programmers.co.kr/learn/courses/30/lessons/12973
public class Prob9RemovePairString {
    public int solution(String s) {
        int length = s.length();

        if(length == 0) return 1;
        if(length == 1) return 0;

        // 왼쪽에 남아있는 문자들을 저장하는 스택
        Deque<Character> remainders = new ArrayDeque<>(length);
        int curIdx = 0;
        while(curIdx < length) {
            // 남아 있는 문자들 중에 삭제해야 하는 문자들 제거
            while(!remainders.isEmpty() && curIdx < length && remainders.peek() == s.charAt(curIdx)) {
                remainders.pop();
                curIdx++;
            }

            // 마지막 문자까지 왔으면 반복문 종료
            if(curIdx >= length) break;

            // 현재 문자 스택에 저장
            remainders.push(s.charAt(curIdx++));
        }

        return remainders.isEmpty() ? 1 : 0;
    }

    public static void main(String[] args) {
        Prob9RemovePairString sol = new Prob9RemovePairString();

        String[] strings = {"baabaa", "cdcd"};
        int[] ans = {1, 0};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            int mySol = sol.solution(strings[i]);
            if (ans[i] == mySol) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
