package from501to600;

// https://leetcode.com/problems/longest-palindromic-subsequence/
public class Prob516LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();

        // dp[i][j] : solution of s.subString(i,j+1)
        int[][] dp = new int[len][len];

        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;

            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // s.subString(i+1,j) 에 i 및 j 문자 추가
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 현재 두 문자는 무시 => 삭제
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
