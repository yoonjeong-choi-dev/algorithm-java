package from1to100;

// https://leetcode.com/problems/sqrtx/
public class Prob69SqrtFunction {
    public int mySqrt(int x) {
        if (x < 2) return x;

        int left = 2, right = x / 2;
        int curSqrt;
        long curPow;

        // Binary Search
        while (left <= right) {
            curSqrt = left + (right - left) / 2;
            curPow = (long) curSqrt * curSqrt;

            if (curPow == x) return curSqrt;
            else if (curPow < x) left = curSqrt + 1;
            else right = curSqrt - 1;
        }

        // right = left-1
        // => right^2 < x < left^2
        return right;
    }
}
