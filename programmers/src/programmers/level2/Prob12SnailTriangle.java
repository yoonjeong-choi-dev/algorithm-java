package programmers.level2;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/68645
public class Prob12SnailTriangle {
    public int[] solution(int n) {
        if (n == 1) return new int[]{1};
        if (n == 2) return new int[]{1, 2, 3};

        int totalSize = n * (n + 1) / 2;
        int[] answer = new int[totalSize];

        int curNum = 1;
        int curLen = n - 1;
        int curStep = 0;

        int row, col;
        while (curLen >= 0) {
            row = curStep * 2;
            col = curStep;
            if (curLen == 0) {
                answer[getIndex(row, col)] = curNum;
                break;
            }

            // left line
            for (int i = 0; i < curLen; i++) {
                answer[getIndex(row + i, col)] = curNum++;
            }

            // bottom line
            for (int i = 0; i < curLen; i++) {
                answer[getIndex(row + curLen, col + i)] = curNum++;
            }

            // left line
            for (int i = 0; i < curLen; i++) {
                answer[getIndex(row + curLen - i, col + curLen -i)] = curNum++;
            }


            curLen -= 3;
            curStep++;
        }


        return answer;
    }

    private int getIndex(int row, int col) {
        return row * (row + 1) / 2 + col;
    }

    public static void main(String[] args) {
        Prob12SnailTriangle sol = new Prob12SnailTriangle();

        int[] n = {4, 5, 6};
        int[][] ans = {
                {1, 2, 9, 3, 10, 8, 4, 5, 6, 7},
                {1, 2, 12, 3, 13, 11, 4, 14, 15, 10, 5, 6, 7, 8, 9},
                {1, 2, 15, 3, 16, 14, 4, 17, 21, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11}
        };

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            int[] mySol = sol.solution(n[i]);

            System.out.printf("\n==================== Problem %d\n", i);
            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
