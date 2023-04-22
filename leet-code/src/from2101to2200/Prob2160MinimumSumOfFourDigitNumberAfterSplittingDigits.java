package from2101to2200;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/
public class Prob2160MinimumSumOfFourDigitNumberAfterSplittingDigits {
    public int minimumSum(int num) {
        int[] digits = new int[4];
        for (int i = 0; i < 4; i++) {
            digits[i] = num % 10;
            num /= 10;
        }
        Arrays.sort(digits);

        int ans = 0;
        for (int i = 0; i < 2; i++) {
            ans += digits[i] * 10 + digits[i + 2];
        }
        return ans;
    }
}
