package from301to400;

import java.util.LinkedHashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
// Ref : Problem 159 -> Generalization
public class Prob340LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int len = s.length();

        if (len <= k) return len;

        int mapBound = k + 1;

        // [0:i] 에서 가장 오른쪽 인덱스 저장
        // 삭제 시, 가장 작은 인덱스를 삭제해야 하므로, 저장 순서가 보장되는 자료 구조 사용
        Map<Character, Integer> rightest = new LinkedHashMap<>();

        int left = 0, right = 0;
        int ans = k;
        char curChar;
        int delIdx;
        while (right < len) {
            // 현재 문자를 포함했을 때 left 인덱스
            curChar = s.charAt(right);
            // 순서 보장을 위해 이미 있는 경우 삭제
            if (rightest.containsKey(curChar)) rightest.remove(curChar);
            rightest.put(curChar, right);

            // 현재 문자 수가 k 초과 => 가장 왼쪽 문자 삭제
            if (rightest.size() == mapBound) {
                delIdx = rightest.values().iterator().next();
                rightest.remove(s.charAt(delIdx));
                left = delIdx + 1;
            }

            right++;
            ans = Math.max(ans, right - left);
        }

        return ans;
    }
}
