package from1301to1400;

// https://leetcode.com/problems/remove-palindromic-subsequences/
public class Prob1332RemovePalindromicSubsequences {
    public int removePalindromeSub(String s) {
        // check whether s is a palindrome
        int left = 0, right = s.length() - 1;
        boolean isPalindrome = true;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                isPalindrome = false;
                break;
            }

            left++;
            right--;
        }

        // Note that a subsequence does not necessarily need to be contiguous.
        // => 문자열이 회문이 아닌 경우, 우선 'a'로 구성된 부분 문자열 삭제=> 나머지 'b'로 구성된 부분 문자열 삭제 : 2번
        return isPalindrome ? 1 : 2;
    }
}
