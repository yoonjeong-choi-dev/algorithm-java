package from1001to1100;

// https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/
public class Prob1100FindKLengthSubstringsWithNoRepeatedCharacters {
    public int numKLenSubstrNoRepeats(String s, int k) {
        // s consists of lowercase English letters
        if (k > 26) return 0;
        int[] occurs = new int[26];

        int len = s.length();

        int ans = 0;
        int left = 0, right = 0, curCharIdx;
        while (right < len) {
            // 현재 문자 추가
            curCharIdx = s.charAt(right++) - 'a';
            occurs[curCharIdx]++;

            // 중복이 없을 때까지 left 이동
            while (occurs[curCharIdx] > 1) {
                occurs[s.charAt(left++) - 'a']--;
            }

            if (right - left>= k) ans++;
        }

        return ans;
    }
}
