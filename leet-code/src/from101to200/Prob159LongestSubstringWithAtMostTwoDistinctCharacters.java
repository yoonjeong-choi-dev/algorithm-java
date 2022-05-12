package from101to200;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
public class Prob159LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int len = s.length();

        if (len < 3) return len;

        // 현재 탐색 범위 [0,i]에서 문자의 가장 오른쪽 위치
        Map<Character, Integer> rightestIndex = new HashMap<>();

        int ans = 1;
        int left = 0, right = 0;
        int delIdx;
        while (right < len) {
            rightestIndex.put(s.charAt(right), right);

            // 현재 사이즈가 2 초과 => 가장 왼쪽에 있는 문자 삭제
            if (rightestIndex.size() == 3) {
                delIdx = right;
                for (Integer val : rightestIndex.values()) delIdx = Math.min(delIdx, val);

                rightestIndex.remove(s.charAt(delIdx));
                left = delIdx + 1;
            }


            right++;
            ans = Math.max(ans, right - left);
        }

        return ans;
    }
}
