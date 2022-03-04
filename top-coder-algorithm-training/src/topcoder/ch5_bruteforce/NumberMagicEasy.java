package topcoder.ch5_bruteforce;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class NumberMagicEasy {

    // 마술에 사용되는 카드
    int[][] cards = {
            {1, 2, 3, 4, 5, 6, 7, 8},
            {1, 2, 3, 4, 9, 10, 11, 12},
            {1, 2, 5, 6, 9, 10, 13, 14},
            {1, 3, 5, 7, 9, 11, 13, 15}
    };


    public int theNumber(String answer) {

        // 각 숫자에 대한 정답
        String[] cheats = new String[16];
        StringBuilder ans = new StringBuilder();
        for (int num = 0; num < 16; num++) {
            ans.setLength(0);
            for (int[] card : cards) {
                if (isContained(num + 1, card)) {
                    ans.append("Y");
                } else {
                    ans.append("N");
                }
            }
            cheats[num] = ans.toString();
        }

        for(int i=0;i<16;i++){

            if(answer.equals(cheats[i])) {
                return i+1;
            }
        }

        return -1;
    }

    private boolean isContained(int target, int[] arr) {
        for (int n : arr) {
            if (n == target) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        NumberMagicEasy sol = new NumberMagicEasy();
        String[] answers = {"YNYY", "YNNN", "NNNN", "YYYY", "NYNY"};

        int[] ans = {5, 8, 16, 1, 11};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.theNumber(answers[i]);
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
