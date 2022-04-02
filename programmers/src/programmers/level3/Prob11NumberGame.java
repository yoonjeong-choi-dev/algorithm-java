package programmers.level3;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/12987
public class Prob11NumberGame {
    public int solution(int[] A, int[] B) {
        int numPeople = A.length;

        // A팀 사람과 B팀 사람을 매칭시키기만 하면 되므로 순서는 중요하지 않음
        // => 양 팀의 숫자를 오름차순으로 정렬
        Arrays.sort(A);
        Arrays.sort(B);

        // A팀의 낮은 숫자들에 대해서 확실하게 이기는 전략
        int answer = 0;
        int curBIndex = 0;
        for (int i = 0; i < numPeople; i++) {
            while (curBIndex < numPeople && A[i] >= B[curBIndex]) {
                curBIndex++;
            }

            // 더이상 이길 수 없는 경우
            if (curBIndex >= numPeople) break;

            answer++;
            curBIndex++;
        }
        return answer;
    }

    public static void main(String[] args) {
        Prob11NumberGame sol = new Prob11NumberGame();

        int[][] A = {{5, 1, 3, 7}, {2, 2, 2, 2}};
        int[][] B = {{2, 2, 6, 8}, {1, 1, 1, 1}};

        int[] ans = {3, 0};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            long mySol = sol.solution(A[i], B[i]);
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
