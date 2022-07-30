package from1to100;

// https://leetcode.com/problems/interleaving-string/
public class Prob97InterleavingString {

    private String s1, s2, s3;
    int[][] cache;

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;

        cache = new int[s1.length()][s2.length()];

        return recursive(0, 0);
    }

    private boolean recursive(int idx1, int idx2) {
        if (idx1 == s1.length()) {
            for (int i = idx2; i < s2.length(); i++) {
                if (s2.charAt(i) != s3.charAt(idx1 + i)) return false;
            }
            return true;
        }

        if (idx2 == s2.length()) {
            for (int i = idx1; i < s1.length(); i++) {
                if (s1.charAt(i) != s3.charAt(idx2 + i)) return false;
            }
            return true;
        }

        if (cache[idx1][idx2] != 0) return cache[idx1][idx2] == 1;

        int curTargetIdx = idx1 + idx2;

        boolean ans = (s3.charAt(curTargetIdx) == s1.charAt(idx1) && recursive(idx1 + 1, idx2))
                || (s3.charAt(curTargetIdx) == s2.charAt(idx2) && recursive(idx1, idx2 + 1));

        cache[idx1][idx2] = ans ? 1 : -1;
        return ans;
    }

    private boolean dpSolution(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        int len1 = s1.length(), len2 = s2.length();
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];

        int idx3;
        for (int idx1 = 0; idx1 <= len1; idx1++) {
            for (int idx2 = 0; idx2 <= len2; idx2++) {
                idx3 = idx1 + idx2 - 1;
                if (idx1 == 0 && idx2 == 0) dp[idx1][idx2] = true;
                else if (idx1 == 0) {
                    dp[idx1][idx2] = dp[idx1][idx2 - 1] && s2.charAt(idx2 - 1) == s3.charAt(idx3);
                } else if (idx2 == 0) {
                    dp[idx1][idx2] = dp[idx1 - 1][idx2] && s1.charAt(idx1 - 1) == s3.charAt(idx3);
                } else {
                    dp[idx1][idx2] = (dp[idx1][idx2 - 1] && s2.charAt(idx2 - 1) == s3.charAt(idx3)) ||
                            (dp[idx1 - 1][idx2] && s1.charAt(idx1 - 1) == s3.charAt(idx3));
                }
            }
        }

        return dp[len1][len2];
    }


}
