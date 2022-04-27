package from1to100;

// https://leetcode.com/problems/decode-ways/
public class Prob91DecodeWays {

    int[] dp;
    String string;
    int len;

    public int numDecodings(String s) {
        string = s;
        len = s.length();

        // dp[i] : s.substring(i) 에 대한 부분 문제
        dp = new int[len];
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '0') {
                // 0 으로 시작하는 경우 디코딩 불가능
                dp[i] = 0;
            } else {
                dp[i] = -1;
            }
        }

        return recursive(0);
    }

    private int recursive(int curIdx) {
        if (curIdx == len) return 1;

        if (dp[curIdx] != -1) return dp[curIdx];

        int ans = 0;
        // 문자 하나만 디코딩에 사용
        ans = recursive(curIdx + 1);

        // 문자 2개 디코딩에 사용
        if (curIdx < len - 1) {
            char curChar = string.charAt(curIdx);
            char nextChar = string.charAt(curIdx + 1);
            if ((curChar == '1') || (curChar == '2' && nextChar <= '6')) {
                ans += recursive(curIdx + 2);
            }
        }


        return dp[curIdx] = ans;
    }
}
