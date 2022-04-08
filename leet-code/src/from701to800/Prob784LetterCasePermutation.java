package from701to800;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/letter-case-permutation/
public class Prob784LetterCasePermutation {

    public List<String> letterCasePermutation(String s) {
        char[] stringArr = s.toCharArray();
        int len = s.length();
        // 알파벳만 고려
        int numAlphabet = 0;
        for (int i = 0; i < len; i++) {
            if (stringArr[i] < '0' || stringArr[i] > '9') {
                numAlphabet++;
            }
        }

        int totalSize = (int) Math.pow(2, numAlphabet);

        List<String> ans = new ArrayList<>(totalSize);

        int curBit;
        char[] ret;
        for (int cur = 0; cur < totalSize; cur++) {
            curBit = cur + 1;
            ret = new char[len];

            for (int i = 0; i < len; i++) {
                if (stringArr[i] < '0' || stringArr[i] > '9') {
                    // 현재 비트를 이용하여 대소문자 변환
                    if ((curBit & 1) == 1) {
                        ret[i] = Character.toUpperCase(stringArr[i]);
                    } else {
                        ret[i] = Character.toLowerCase(stringArr[i]);
                    }
                    curBit = curBit >> 1;
                } else {
                    ret[i] = stringArr[i];
                }
            }

            ans.add(new String(ret));
        }
        return ans;
    }


    public static void main(String[] args) {
        Prob784LetterCasePermutation sol = new Prob784LetterCasePermutation();

        String[] strings = {"a1b2", "3z4"};

        String[][] ans = {
                {"a1b2", "a1B2", "A1b2", "A1B2"},
                {"3z4", "3Z4"}
        };
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            List<String> mySol = sol.letterCasePermutation(strings[i]);

            System.out.println("Result : " + mySol);
            System.out.println("Expected : " + Arrays.toString(ans[i]));
        }

    }
}
