package from1to100;

// https://leetcode.com/problems/longest-valid-parentheses/
public class Prob32LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int ans = 0;

        // dp[i] = s.charAt(i) 로 끝나는 최대 유효 길이
        int len = s.length();
        int[] dp = new int[len];

        for (int i = 1; i < len; i++) {
            // 괄호가 닫히는 경우만 생각
            // Case 1 : ...() 형태
            // Case 2 : (...) 형태
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // {s.charAt(i-2) 로 끝나는 유효 문자열}() 인 경우
                    // => ... 에 대한 정답 +2
                    dp[i] = 2;
                    dp[i] += i >= 2 ? dp[i - 2] : 0;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    // ({s.charAt(i-1) 로 끝나는 유효 문자열})
                    // i-dp[i-1] : s.charAt(i-1) 로 끝나는 유효 문자열의 시작 부분
                    dp[i] = dp[i - 1] + 2;
                    dp[i] += (i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0;
                }

                ans = Math.max(ans, dp[i]);
            }
        }

        return ans;
    }
}
