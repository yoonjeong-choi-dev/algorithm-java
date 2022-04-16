package from1to100;

// https://leetcode.com/problems/regular-expression-matching/
public class Prob10RegularExpressionMatching {
    private String target;
    private char[] regexArr;
    private boolean[] isStar;
    private int targetLen, regexLen;

    private int[][] cache;

    public boolean isMatch(String s, String p) {
        return myRecursiveSolution(s, p);
        //return myDpSolution(s,p);
    }

    private boolean myRecursiveSolution(String s, String p) {
        target = s;
        targetLen = target.length();

        int numWild = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') numWild++;
        }

        regexLen = p.length() - numWild;
        regexArr = new char[regexLen];
        isStar = new boolean[regexLen];

        int curIdx = 0;
        for (int i = 0; i < p.length(); i++) {
            regexArr[curIdx] = p.charAt(i);
            if (i + 1 < p.length() && p.charAt(i + 1) == '*') {
                i++;
                isStar[curIdx] = true;
            }
            curIdx++;
        }

        return recursiveSearch(0, 0);
    }

    private boolean recursiveSearch(int targetIdx, int regexIdx) {
        if (targetIdx == targetLen) {
            // 남은 정규표현식이 없거나 와일드카드면 참
            boolean ans = true;
            for (int i = regexIdx; i < regexLen; i++) ans &= isStar[i];
            return ans;
        } else if (regexIdx == regexLen) {
            // 정규 표현식만 다 탐색한 경우는 거짓
            return false;
        }

        char curRegChar = regexArr[regexIdx];
        boolean ans = false;

        // * 사용없이 검사
        if (curRegChar == '.' || curRegChar == target.charAt(targetIdx)) {
            ans = recursiveSearch(targetIdx + 1, regexIdx + 1);
        }

        // 매칭되는 결과가 있는 경우 조기 종료
        if (ans) return true;

        // 매칭이 안되고 와일드 카드도 아닌 경우 더 이상 탐색 불필요
        if (!isStar[regexIdx]) return false;

        // 와일드 카드 사용
        // 0개 사용
        ans = recursiveSearch(targetIdx, regexIdx + 1);
        if (ans) return true;

        // 1개 이상 사용
        if (curRegChar == '.' || target.charAt(targetIdx) == curRegChar) {
            return recursiveSearch(targetIdx + 1, regexIdx);
        } else {
            return false;
        }
    }

    private boolean myDpSolution(String s, String p) {
        target = s;
        targetLen = target.length();

        int numWild = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') numWild++;
        }

        regexLen = p.length() - numWild;
        regexArr = new char[regexLen];
        isStar = new boolean[regexLen];

        cache = new int[targetLen][regexLen];
        for (int i = 0; i < targetLen; i++) {
            for (int j = 0; j < regexLen; j++) {
                cache[i][j] = -1;
            }
        }

        int curIdx = 0;
        for (int i = 0; i < p.length(); i++) {
            regexArr[curIdx] = p.charAt(i);
            if (i + 1 < p.length() && p.charAt(i + 1) == '*') {
                i++;
                isStar[curIdx] = true;
            }
            curIdx++;
        }

        return dp(0, 0) == 1;
    }

    private int dp(int targetIdx, int regexIdx) {
        if (targetIdx == targetLen) {
            // 남은 정규표현식이 없거나 와일드카드면 참
            boolean ans = true;
            for (int i = regexIdx; i < regexLen; i++) ans &= isStar[i];
            return ans ? 1 : -1;
        } else if (regexIdx == regexLen) {
            // 정규 표현식만 다 탐색한 경우는 거짓
            return -1;
        }

        if (cache[targetIdx][regexIdx] != -1) return cache[targetIdx][regexIdx];

        char curRegChar = regexArr[regexIdx];
        int ans = -1;

        // * 사용없이 검사
        if (curRegChar == '.' || curRegChar == target.charAt(targetIdx)) {
            ans = dp(targetIdx + 1, regexIdx + 1);
        }

        // 매칭되는 결과가 있는 경우 조기 종료
        if (ans == 1) {
            return cache[targetIdx][regexIdx] = 1;
        }

        // 매칭이 안되고 와일드 카드도 아닌 경우 더 이상 탐색 불필요
        if (!isStar[regexIdx]) {
            return cache[targetIdx][regexIdx] = -1;
        }

        // 와일드 카드 사용
        // 0개 사용
        ans = dp(targetIdx, regexIdx + 1);
        if (ans == 1) {
            return cache[targetIdx][regexIdx] = 1;
        }

        // 1개 이상 사용
        if (curRegChar == '.' || target.charAt(targetIdx) == curRegChar) {
            return cache[targetIdx][regexIdx] = dp(targetIdx + 1, regexIdx);
        } else {
            return cache[targetIdx][regexIdx] = -1;
        }
    }


    public static void main(String[] args) {
        Prob10RegularExpressionMatching sol = new Prob10RegularExpressionMatching();

        String[] targets = {"aa", "aa", "ab", "aab", "mississippi", "a"};
        String[] regex = {"a", "a*", ".*", "c*a*b", "mis*is*p*.", "ab*"};

        boolean[] ans = {false, true, true, true, false, true};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            boolean mySol = sol.isMatch(targets[i], regex[i]);
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
