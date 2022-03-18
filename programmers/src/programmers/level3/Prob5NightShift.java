package programmers.level3;

import java.util.Collections;
import java.util.PriorityQueue;

// https://programmers.co.kr/learn/courses/30/lessons/12927
public class Prob5NightShift {
    public long solution(int n, int[] works) {
        // 남은 작업량의 최대값을 추적
        // => 제곱 합을 최소로 하기 위해서는 배열의 최대값을 줄여야 함
        PriorityQueue<Integer> remains = new PriorityQueue<>(works.length, Collections.reverseOrder());
        for (int work : works) remains.add(work);

        int first, second, diff;
        while (n > 0 && !remains.isEmpty()) {
            // 남은 작업이 1개인 경우
            if (remains.size() == 1) {
                first = remains.poll();
                first = first >= n ? first - n : 0;
                return ((long) first) * first;
            }

            // 2개 이상인 경우, 가장 큰값을 두번 째보다 작게만든다
            first = remains.poll();
            second = remains.poll();
            diff = first - second;

            if (diff + 1 <= n) {
                n -= diff + 1;
                first -= diff + 1;
            } else {
                first -= n;
                n = 0;
            }

            // 첫번째 업무가 남아 있는 경우 다시 큐에 넣는다
            if (first > 0) remains.add(first);
            remains.add(second);
        }

        long answer = 0;
        while (!remains.isEmpty()) {
            first = remains.poll();
            answer += ((long) first) * first;
        }
        return answer;
    }

    public static void main(String[] args) {
        Prob5NightShift sol = new Prob5NightShift();
        int[][] works = {{4, 3, 3}, {2, 1, 2}, {1, 1}};
        int[] n = {4, 1, 3};

        long[] ans = {12, 6, 0};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            long mySol = sol.solution(n[i], works[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
