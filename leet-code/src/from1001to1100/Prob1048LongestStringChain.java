package from1001to1100;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-string-chain/
public class Prob1048LongestStringChain {
    public int longestStrChain(String[] words) {
        // 문자열 길이로 정렬
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        int ans = 1;

        // dp.get(word) : word 로 끝나는 체인의 가장 긴 길이
        Map<String, Integer> dp = new HashMap<>();
        for (String word : words) {
            // word 로 끝나느 체인 길이 구하기
            int maxLen = 1;
            StringBuilder sb;

            // word 에서 한 문자씩 제거
            for (int i = 0; i < word.length(); i++) {
                sb = new StringBuilder(word);
                sb.deleteCharAt(i);
                maxLen = Math.max(maxLen, dp.getOrDefault(sb.toString(), 0) + 1);
            }

            dp.put(word, maxLen);
            ans = Math.max(ans, maxLen);
        }
        return ans;
    }
}
