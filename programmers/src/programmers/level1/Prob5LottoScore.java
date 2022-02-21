package programmers.level1;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/77484
public class Prob5LottoScore {
    public int[] solution(int[] lottos, int[] win_nums) {
        int correctNum = 0, zeroNum = 0;
        for(int num : lottos) {
            if (num == 0) {
                zeroNum++;
                continue;
            }

            for(int win : win_nums) {
                if(num == win) {
                    correctNum++;
                    break;
                }
            }
        }

        // 최소 점수 : 와일드카드를 모두 틀린 번호
        int minScore = correctNum < 2 ? 6 : 7 - correctNum;
        // 최대 점수 : 와일드카드를 모두 맞는 번호
        int maxScore = correctNum + zeroNum < 2 ? 6 : 7 - (correctNum + zeroNum);
        return new int[] {maxScore, minScore};
    }

    public static void main(String[] args) {
        Prob5LottoScore sol = new Prob5LottoScore();

        int[][] lottos = new int[][]{
                {44, 1, 0, 0, 31, 25},
                {0, 0, 0, 0, 0, 0},
                {45, 4, 35, 20, 3, 9}
        };
        int[][] winNums = new int[][]{
                {31, 10, 45, 1, 6, 19},
                {38, 19, 20, 40, 15, 25},
                {20, 9, 3, 45, 4, 35}
        };

        int[][] ans = new int[][]{
                {3, 5}, {1, 6}, {1, 1}
        };

        int numProblems = lottos.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int[] mySol = sol.solution(lottos[i], winNums[i]);
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
