package from1to100;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// https://leetcode.com/problems/palindrome-number/
public class Prob9PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;

        List<Integer> digits = new ArrayList<>();
        while (x != 0) {
            digits.add(x % 10);
            x /= 10;
        }

        int left = 0, right = digits.size() - 1;
        while (left < right) {
            if (!Objects.equals(digits.get(left++), digits.get(right--))) return false;
        }

        return true;
    }
}
