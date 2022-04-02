package from1201to1300;

// https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
public class Prob1281SubtractProductAndSum {
    public int subtractProductAndSum(int n) {
        int product = 1;
        int sum = 0;

        int digit;
        while (n != 0) {
            digit = n % 10;
            product *= digit;
            sum += digit;

            n /= 10;
        }

        return product - sum;
    }
}
