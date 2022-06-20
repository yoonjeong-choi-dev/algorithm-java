package from101to200;

import java.util.*;

// https://leetcode.com/problems/word-break-ii/
// Ref : Problem 139
public class Prob140WordBreak2 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        // 단어 존재 유무를 빠르게 확인하기 위해 해시셋 사용
        Set<String> dictSet = new HashSet<>(wordDict);

        // dp[i] : s.substring(0,i)에 대한 부분 문제
        int len = s.length();
        List<List<String>> dp = new ArrayList<>(len + 1);
        for (int i = 0; i <= len; i++) dp.add(new ArrayList<>());

        // 처음은 항상 정답!
        dp.get(0).add("");

        String subStr;
        for (int end = 1; end <= len; end++) {
            for (int start = 0; start < end; start++) {
                // s.substring(0,start) 부분 문제 정답에 s.substring(start, end) 추가
                subStr = s.substring(start, end);

                if (!dp.get(start).isEmpty() && dictSet.contains(subStr)) {

                    for(String prev : dp.get(start)) {
                        // dp.get(0) 에 있는 빈 문자열 처리
                        if(prev.isEmpty()) dp.get(end).add(subStr);
                        else dp.get(end).add(String.format("%s %s", prev, subStr));
                    }
                }
            }
        }


        return dp.get(len);
    }
}
