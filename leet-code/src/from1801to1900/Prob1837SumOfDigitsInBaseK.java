package from1801to1900;

import java.util.List;

// https://leetcode.com/problems/sum-of-digits-in-base-k/
public class Prob1837SumOfDigitsInBaseK {
    public int sumBase(int n, int k) {
        int ans = 0;
        while (n != 0) {
            ans += n % k;
            n /= k;
        }
        return ans;
    }
}
