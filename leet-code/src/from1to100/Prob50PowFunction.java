package from1to100;

// https://leetcode.com/problems/powx-n/
public class Prob50PowFunction {
    public double myPow(double x, int n) {
        if (n == 0) return 1.0;
        if (n == 1) return x;

        if (n < 0) {
            if (n == Integer.MIN_VALUE) {
                n++;
                n = -n;
                x = 1 / x / x;
            } else {
                n = -n;
                x = 1 / x;
            }
        }

        return recursivePow(x, n);
    }

    private double recursivePow(double x, int n) {
        if (n == 0) return 1.0;
        if (n == 1) return x;

        double ret = recursivePow(x, n / 2);
        ret = ret * ret;

        if (n % 2 == 1) ret *= x;
        return ret;
    }
}
