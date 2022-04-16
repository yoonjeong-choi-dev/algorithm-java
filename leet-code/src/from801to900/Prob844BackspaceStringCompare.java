package from801to900;

// https://leetcode.com/problems/backspace-string-compare/
public class Prob844BackspaceStringCompare {
    public boolean backspaceCompare(String s1, String s2) {
        //return mySolutionWithCreationAnotherString(s1, s2);
        return twoPointerSolution(s1, s2);
    }

    private boolean mySolutionWithCreationAnotherString(String s1, String s2) {
        return createResultConsideringBackspace(s1).equals(createResultConsideringBackspace(s2));
    }

    private String createResultConsideringBackspace(String s) {
        int len = s.length();
        char[] ret = new char[len];

        int curIdx = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '#') {
                if (curIdx > 0) curIdx--;
            } else {
                ret[curIdx++] = s.charAt(i);
            }
        }

        return String.copyValueOf(ret, 0, curIdx);
    }

    // TODO : Use Two Pointer
    private boolean twoPointerSolution(String s, String t) {
        int idx1 = s.length() - 1, idx2 = t.length() - 1;
        int numBack1 = 0, numBack2 = 0;

        while (idx1 >= 0 || idx2 >= 0) {
            while (idx1 >= 0) {
                if (s.charAt(idx1) == '#') {
                    numBack1++;
                    idx1--;
                } else if (numBack1 > 0) {
                    // Remove by backspace
                    numBack1--;
                    idx1--;
                } else {
                    break;
                }
            }

            while (idx2 >= 0) {
                if (t.charAt(idx2) == '#') {
                    numBack2++;
                    idx2--;
                } else if (numBack2 > 0) {
                    // Remove by backspace
                    numBack2--;
                    idx2--;
                } else {
                    break;
                }
            }

            // 백스페이스를 고려한 실제 문자 비교
            if (idx1 >= 0 && idx2 >= 0 && s.charAt(idx1) != t.charAt(idx2)) return false;

            // 둘 중 하나만 탐색이 끝난 경우
            if ((idx1 >= 0) != (idx2 >= 0)) return false;

            idx1--;
            idx2--;
        }

        return true;
    }

    public static void main(String[] args) {
        Prob844BackspaceStringCompare sol = new Prob844BackspaceStringCompare();
        String[] strings1 = {"ab#c", "ab##", "a#c", "y#fo##f"};
        String[] strings2 = {"ad#c", "c#d#", "b", "y#f#o##f"};

        boolean[] ans = {true, true, false, true};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            boolean mySol = sol.backspaceCompare(strings1[i], strings2[i]);
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
