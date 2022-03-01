package programmers.level1;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/42889
public class Prob26FailOver {

    public int[] solution(int N, int[] stages) {
        int numPlayers = stages.length;

        int[] numChallengers = new int[N + 1];
        for (int stage : stages) {
            numChallengers[stage - 1]++;
        }


        double[] failover = new double[N];
        for (int i = 0; i < N; i++) {
            if (numPlayers == 0) break;
            failover[i] = (double) numChallengers[i] / numPlayers;
            numPlayers -= numChallengers[i];
        }

        int[] answer = new int[N];
        for (int i = 0; i < N; i++) answer[i] = i;

        int curIdxValue, insertIdx;
        for (int i = 1; i < N; i++) {
            curIdxValue = answer[i];    // 비교 기준이 되는 인덱스 값
            insertIdx = i - 1;          // 삽입 위치 : curIdxValue를 넣어야 하는 곳

            // [0, i-1] 까지 정렬되어 있다고 가정
            // answer[i]를 넣을 위치(insertIdx)를 찾아서 삽입
            while ((insertIdx >= 0) && (failover[answer[insertIdx]] < failover[curIdxValue])) {
                answer[insertIdx + 1] = answer[insertIdx];
                insertIdx--;
            }
            answer[insertIdx + 1] = curIdxValue;
        }

        for (int i = 0; i < N; i++) answer[i]++;
        return answer;
    }

    public static void main(String[] args) {
        Prob26FailOver sol = new Prob26FailOver();

        int[] N = {5,4};
        int[][] stages = {{2, 1, 2, 6, 2, 4, 3, 3}, {4,4,4,4,4}};

        int[][] ans = {{3,4,2,1,5}, {4,1,2,3}};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int[] mySol = sol.solution(N[i], stages[i]);

            if (Arrays.equals(ans[i], mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }

    }

}