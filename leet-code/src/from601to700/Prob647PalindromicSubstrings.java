package from601to700;

// https://leetcode.com/problems/palindromic-substrings/
public class Prob647PalindromicSubstrings {
    public int countSubstrings(String s) {
        int len = s.length();

        int ans = 0;

        int left, right;
        for (int mid = 0; mid < len; mid++) {
            // (mid, mid+1) 을 중심으로 하는 짝수 길이의 회문
            left = mid;
            right = mid + 1;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }

            // (mid,mid) 을 중심으로 하는 홀수 길이의 회문
            left = right = mid;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }

        return ans;
    }
}
