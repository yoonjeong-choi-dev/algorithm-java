package from1to100;

// https://leetcode.com/problems/longest-palindromic-substring/
public class Prob5LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int len = s.length();

        int maxStart = 0, maxEnd = 0;
        int left, right, curLen;
        for (int i = 0; i < s.length(); i++) {
            // (i, i+1)을 중심으로 하는 회문 길이 검색
            left = i;
            right = i + 1;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            curLen = right - left - 1;
            if (maxEnd - maxStart < curLen) {
                maxEnd = right;
                maxStart = left + 1;
            }

            // (i,i)를 중심으로 하는 회문 길이 검색
            left = i;
            right = i;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            curLen = right - left - 1;
            if (maxEnd - maxStart < curLen) {
                maxEnd = right;
                maxStart = left + 1;
            }
        }


        return s.substring(maxStart, maxEnd);
    }
}
