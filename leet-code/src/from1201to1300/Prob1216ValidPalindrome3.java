package from1201to1300;

// https://leetcode.com/problems/valid-palindrome-iii/
// Ref : Problem 516
public class Prob1216ValidPalindrome3 {
    public boolean isValidPalindrome(String s, int k) {
        int len = s.length();

        // dp[i][j] : s[i:j] 에서 만들 수 있는 최대 회문 길이
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                // s[i+1:j-1]에 추가 가능 여부 확인
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    // 둘 중 하나는 제거하고, s[i+1:j] 및 s[i:j-1] 중 최대 값 사용
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        // 삭제해야 하는 문자 최소 개수 : len - dp[0][len-1]
        return len - dp[0][len-1] <= k;
    }
}
