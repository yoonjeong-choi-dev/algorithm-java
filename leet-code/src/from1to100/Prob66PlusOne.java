package from1to100;

// https://leetcode.com/problems/plus-one/
public class Prob66PlusOne {
    public int[] plusOne(int[] digits) {
        digits[digits.length-1]++;
        boolean isCarry = false;

        for (int i = digits.length - 1; i >= 0; i--) {
            if (isCarry) digits[i]++;
            if (digits[i] == 10) {
                digits[i] = 0;
                isCarry = true;
            } else {
                isCarry = false;
            }
        }

        if (isCarry) {
            int[] ans = new int[digits.length + 1];
            ans[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                ans[i + 1] = digits[i];
            }
            return ans;
        } else {
            return digits;
        }
    }
}
