package from1101to1200;

// https://leetcode.com/problems/longest-common-subsequence/
public class Prob1143LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        // dp[i][j] : text1[i:] text2[j:] 부분 문제에 대한 정답
        int len1 = text1.length(), len2 = text2.length();

        // bottom 및 right 을 0으로 만들어주 가장자리 엣지 케이스 처리
        int[][] dp = new int[len1 + 1][len2 + 1];

        // bottom up
        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    // 현재 문자가 같은 경우 추가 : (i+1,j+1) 하위 문제 정답 + 1
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    // 현재 문자가 다른 경우 => 양 하위 문제들의 최대값
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        return dp[0][0];
    }
}
