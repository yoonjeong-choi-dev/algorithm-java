package from1to100;

// https://leetcode.com/problems/divide-two-integers/
public class Prob29DivideTwoIntegers {
    // divide two integers without using multiplication, division, and mod operator.
    public int divide(int dividend, int divisor) {
        // Edge Case 1 : stack overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            // 결과 : 2^31 where Integer.MAX_VALUE == 2^31-1
            return Integer.MAX_VALUE;
        }

        // Edge Case 2 : abs(divisor) == 1
        if (divisor == 1) return dividend;
        if (divisor == -1) return -dividend;

        // 우선 두 피연산자를 양수로 변환하여 계산
        int numNegative = 0;
        if (dividend < 0) {
            numNegative++;
            dividend = -dividend;
        }

        if (divisor < 0) {
            numNegative++;
            divisor = -divisor;
        }


        int ret = 0;
        while (dividend - divisor >= 0) {
            ret++;
            dividend -= divisor;
        }

        if (numNegative == 1) ret = -ret;
        return ret;
    }
}
