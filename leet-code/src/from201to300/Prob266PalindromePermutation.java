package from201to300;

// https://leetcode.com/problems/palindrome-permutation/
public class Prob266PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        // s consists of only lowercase English letters
        int[] counter = new int[26];
        for (char c : s.toCharArray()) counter[c - 'a']++;

        // 홀수만큼 나온 문자 개수 추적
        int oddNum = 0;
        for (int c : counter)
            if (c % 2 == 1) oddNum++;

        // 문자열 길이가 홀수인 경우에만 홀수만큼 있는 문자 1개 허용
        return s.length() % 2 == 1 ? oddNum == 1 : oddNum == 0;
    }
}
