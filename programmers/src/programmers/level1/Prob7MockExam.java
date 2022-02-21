package programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/42840
public class Prob7MockExam {
    static int[][] patterns = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
    };

    static int[] patternLen;

    {
        patternLen = new int[patterns.length];
        for (int i = 0; i < patterns.length; i++) {
            patternLen[i] = patterns[i].length;
        }
    }

    static int[] scores = new int[3];

    public int[] solution(int[] answers) {

        int numProbs = answers.length;

        for (int i = 0; i < patterns.length; i++) {
            scores[i] = 0;

            for (int j = 0; j < numProbs; j++) {
                if (answers[j] == patterns[i][j % patternLen[i]]) scores[i]++;
            }
        }

        int maxScore = scores[0];
        for (int i = 1; i < patterns.length; i++) {
            maxScore = Math.max(maxScore, scores[i]);
        }

        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = 0; i < patterns.length; i++) {
            if (scores[i] == maxScore) ret.add(i);
        }

        int[] answer = new int[ret.size()];
        for (int i = 0; i < ret.size(); i++) {
            answer[i] = ret.get(i) + 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        Prob7MockExam sol = new Prob7MockExam();

        int[][] answers = {{1, 2, 3, 4, 5}, {1, 3, 2, 4, 2}};
        int[][] ans = {{1}, {1, 2, 3}};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int[] mySol = sol.solution(answers[i]);
            if (Arrays.equals(ans[i], mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
