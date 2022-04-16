package from1to100;

// https://leetcode.com/problems/implement-strstr/
public class Prob28ImplementStr {

    // Java indexOf 구현
    public int strStr(String haystack, String needle) {

        int wordLen = haystack.length();
        int targetLen = needle.length();

        if (targetLen == 0) return 0;


        boolean isContain;
        for (int i = 0; i <= wordLen - targetLen; i++) {
            if (haystack.charAt(i) != needle.charAt(0)) continue;

            isContain = true;
            for (int j = 1; j < targetLen; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    isContain = false;
                    break;
                }
            }

            if (isContain) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Prob28ImplementStr sol = new Prob28ImplementStr();

        String[] words = {"hello", "aaaaa", "a", "abc"};
        String[] targets = {"ll", "bba", "a", "c"};

        int[] ans = {2, -1, 0, 2};
        int numProblems = ans.length;

        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.strStr(words[i], targets[i]);
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
