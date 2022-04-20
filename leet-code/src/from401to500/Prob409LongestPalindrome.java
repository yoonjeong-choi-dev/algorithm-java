package from401to500;

// https://leetcode.com/problems/longest-palindrome/
public class Prob409LongestPalindrome {
    public int longestPalindrome(String s) {
        int[] count = new int[128];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        int ans = 0;
        boolean hasOddCount = false;
        for (int i = 0; i < 128; i++) {
            ans += (count[i] / 2) * 2;

            if (!hasOddCount && count[i] % 2 == 1) hasOddCount = true;
        }

        // 홀수 개의 문자가 있는 경우 가운데에 추가 가능
        if(hasOddCount) ans++;

        return ans;
    }
}
