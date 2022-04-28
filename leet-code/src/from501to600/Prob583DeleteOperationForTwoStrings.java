package from501to600;

// https://leetcode.com/problems/delete-operation-for-two-strings/
public class Prob583DeleteOperationForTwoStrings {
    public int minDistance(String word1, String word2) {
        // 두 문자열에서 문자를 삭제하여 동일하게 만들기
        // => 두 문자열의 공통 Subsequence 의 최대 길이 구하기
        // => Ref : Problem 1143
        int len1 = word1.length(), len2 = word2.length();

        // dp[i][j] : word1[i:] 와 word2[j:] 의 공통 Subsequence 의 최대 길이
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return len1 + len2 - 2 * dp[0][0];
    }
}
