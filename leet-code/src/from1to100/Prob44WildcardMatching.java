package from1to100;

// https://leetcode.com/problems/wildcard-matching/
public class Prob44WildcardMatching {
    private String target;
    private int targetLen;
    private String pattern;
    private int patternLen;
    private int[][] cache;

    public boolean isMatch(String s, String p) {
        target = s;
        targetLen = s.length();
        pattern = p;
        patternLen = p.length();
        cache = new int[targetLen + 1][patternLen + 1];
        for (int i = 0; i <= targetLen; i++) {
            for (int j = 0; j <= patternLen; j++) {
                cache[i][j] = -1;
            }
        }

        return recursive(0, 0) == 1;
    }

    private int recursive(int targetIdx, int patternIdx) {
        if (cache[targetIdx][patternIdx] != -1) return cache[targetIdx][patternIdx];

        if (targetIdx == targetLen) {
            // 남은 정규표현식이 없거나 와일드카드면 참
            boolean ans = true;
            for (int i = patternIdx; i < patternLen; i++) {
                if (pattern.charAt(i) != '*') {
                    ans = false;
                    break;
                }
            }
            cache[targetIdx][patternIdx] = ans ? 1 : 0;
            return cache[targetIdx][patternIdx];
        } else if (patternIdx == patternLen) {
            // 정규 표현식만 다 탐색한 경우는 거짓
            return cache[targetIdx][patternIdx] = 0;
        }

        char curPattern = pattern.charAt(patternIdx);

        // * 와일드 카드가 아닌 경우
        if (curPattern != '*') {
            if (curPattern == '?' || target.charAt(targetIdx) == curPattern) {
                return cache[targetIdx][patternIdx] = recursive(targetIdx + 1, patternIdx + 1);
            } else {
                return cache[targetIdx][patternIdx] = 0;
            }
        }

        // * 와일드 카드로 처리할 문제
        int curRemainTargetLen = targetLen - targetIdx;
        int ans = 0;
        for (int i = 0; i <= curRemainTargetLen; i++) {
            ans = recursive(targetIdx + i, patternIdx + 1);
            if (ans == 1) {
                return cache[targetIdx][patternIdx] = 1;
            }
        }

        return cache[targetIdx][patternIdx] = 0;
    }


    private boolean solutionWithDP(String s, String p) {
        int targetLen = s.length();
        int patternLen = p.length();

        // Base Case
        if (s.equals(p)) return true;
        boolean isAllStart = patternLen != 0;
        for (int i = 0; i < patternLen; i++) {
            if (p.charAt(i) != '*') {
                isAllStart = false;
                break;
            }
        }
        if (isAllStart) return true;
        if (targetLen == 0 || patternLen == 0) return false;


        // dp[i][j] : s[0:i+1] 와 p[0:j+1] 에 대한 부분 문제
        boolean[][] dp = new boolean[targetLen + 1][patternLen + 1];
        dp[0][0] = true;

        int targetIdx;
        char curPattern;
        for (int patternIdx = 0; patternIdx < patternLen; patternIdx++) {
            // dp[?][patternIdx+1] 계산
            curPattern = p.charAt(patternIdx);
            if (curPattern == '*') {
                // * 와일드 카드인 경우
                // s[0:i] 와 p[0:curPatternIdx-1]이 일치하는 가장 작은 인덱스 찾기
                // => * 는 뒤에 붙이기만 하면 되므로
                targetIdx = 1;
                while (targetIdx <= targetLen && !dp[targetIdx-1][patternIdx]) targetIdx++;
                dp[targetIdx - 1][patternIdx + 1] = dp[targetIdx - 1][patternIdx];

                // targetIdx 이후부터는 현재 와일드카드로 처리 가능하므로 True
                while (targetIdx <= targetLen) dp[targetIdx++][patternIdx + 1] = true;
            } else if (curPattern == '?') {
                // dp[i][patternIdx+1] <- dp[i-1][patternIdx] : 현재 문자는 무시하면 됨
                for (targetIdx = 0; targetIdx < targetLen; targetIdx++)
                    dp[targetIdx + 1][patternIdx + 1] = dp[targetIdx][patternIdx];
            } else {
                // dp[i][patternIdx+1] : dp[i-1][patternIdx] && 문자 일치 여부
                for (targetIdx = 0; targetIdx < targetLen; targetIdx++){
                    System.out.println(targetIdx);
                    dp[targetIdx + 1][patternIdx + 1] = dp[targetIdx][patternIdx] && (s.charAt(targetIdx) == curPattern);
                }

            }
        }


        return dp[targetLen][patternLen];
    }
}
