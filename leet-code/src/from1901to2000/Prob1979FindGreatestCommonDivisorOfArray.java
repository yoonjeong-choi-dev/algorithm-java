package from1901to2000;

// https://leetcode.com/problems/find-greatest-common-divisor-of-array/
public class Prob1979FindGreatestCommonDivisorOfArray {
    public int findGCD(int[] nums) {
        // Step 1 : find min and max values
        int min = nums[0], max = nums[0];
        for (int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }

        // Step 2 : calculate g.c.d
        // min <- max % min && max <- min => gcd == max
        int temp;
        while (min != 0) {
            temp = max % min;
            max = min;
            min = temp;
        }
        return max;
    }
}
