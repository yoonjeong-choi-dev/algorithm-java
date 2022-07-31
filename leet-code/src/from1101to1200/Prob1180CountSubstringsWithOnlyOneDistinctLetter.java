package from1101to1200;

// https://leetcode.com/problems/count-substrings-with-only-one-distinct-letter/
public class Prob1180CountSubstringsWithOnlyOneDistinctLetter {
    public int countLetters(String s) {
        int len = s.length();
        int ans = 0;

        char c;
        int curLen;
        int idx = 0;
        while (idx < len) {
            c = s.charAt(idx);

            // c 연속 길이
            curLen = 0;
            while (idx < len && s.charAt(idx) == c) {
                idx++;
                curLen++;
            }

            // 길이가 n(<len) 인 문자열 개수 : len-n+1
            // => 1 ~ len 까지의 합
            ans += curLen * (curLen + 1) / 2;
        }

        return ans;
    }
}
