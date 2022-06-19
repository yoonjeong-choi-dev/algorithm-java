package from101to200;

// https://leetcode.com/problems/valid-palindrome/
public class Prob125ValidPalindrome {
    public boolean isPalindrome(String s) {
        // Convert : converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters
        StringBuilder sb = new StringBuilder();
        char curChar;
        for (int i = 0; i < s.length(); i++) {
            curChar = s.charAt(i);
            if (Character.isAlphabetic(curChar)) {
                sb.append(Character.toLowerCase(curChar));
            } else if (Character.isDigit(curChar)) {
                sb.append(curChar);
            }
        }

        String converted = sb.toString();
        int left = 0, right = converted.length() - 1;
        while (left < right) {
            if (converted.charAt(left) != converted.charAt(right)) return false;
            left++;
            right--;
        }

        return true;
    }
}
