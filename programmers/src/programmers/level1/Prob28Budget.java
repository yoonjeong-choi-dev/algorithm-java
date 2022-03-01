package programmers.level1;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/12982
public class Prob28Budget {
    public int solution(int[] d, int budget) {
        // 모든 물건의 가치가 1인 냅색 문제
        // => 가장 작은 예산의 부서부터 할당하면 됨
        Arrays.sort(d);

        int answer = 0;
        int curBudget = 0;
        for (int i = 0; i < d.length; i++) {
            if (curBudget + d[i] > budget) break;

            answer++;
            curBudget += d[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        Prob28Budget sol = new Prob28Budget();

        int[][] d = {{1, 3, 2, 5, 4}, {2, 2, 3, 3}};
        int[] budget = {9, 10};

        int[] ans = {3, 4};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int mySol = sol.solution(d[i], budget[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
