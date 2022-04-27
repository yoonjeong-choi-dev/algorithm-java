package from101to200;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/word-break/
public class Prob139WordBreak {
    // 문제 : true if s can be segmented into a space-separated sequence of one or more dictionary words
    // => 문자열이 wordDict 안의 1개 이상의 단어들로 파티션이 되는 경우 true
    public boolean wordBreak(String s, List<String> wordDict) {
        // O(log(N)) 속도로 해당 단어가 있는지 확인하기 위해 Set 자료구조 사용
        Set<String> words = new HashSet<>(wordDict);

        // dp[i] : s.substring(0,i)(0~(i-1))에 대한 부분 문제
        // dp[i] : s.substring(j,i)(j<i)들 중 하나라도 words 에 존재하고, dp[j]가 참인 경우 참
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        // dp[i] 계산
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // s.substring(0,j) 까지 참인 경우, s.substring(j,i) 가 사전에 있는 경우
                // => s.substring(0,j) + s.substring(j,i) == s.substring(0,i) 부분문제에 대해서 참
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}
