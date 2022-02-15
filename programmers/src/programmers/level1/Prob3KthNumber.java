package programmers.level1;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/42748
class Prob3KthNumberSolution {
    public int[] solution(int[] array, int[][] commands) {
        int len = commands.length;
        int[] answer = new int[len];

        for (int i = 0; i < len; i++) {
            int[] sub = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
            Arrays.sort(sub);
            answer[i] = sub[commands[i][2] - 1];
        }
        return answer;
    }
}

public class Prob3KthNumber {
    public static void main(String[] args) {
        Prob3KthNumberSolution sol = new Prob3KthNumberSolution();

        int[] ans = new int[] {5,6,3};
        int[] mySol= sol.solution(new int[] {1, 5, 2, 6, 3, 7, 4},
                new int[][] {{2,5,3}, {4,4,1},{1,7,3}});

        if (Arrays.equals(ans, mySol)) {
            System.out.println("PASS");
        } else {
            System.out.println("Wrong");
            System.out.println("Solution : " + Arrays.toString(mySol));
            System.out.println("Expected : " + Arrays.toString(ans));
        }
    }
}
