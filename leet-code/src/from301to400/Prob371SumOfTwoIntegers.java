package from301to400;

// https://leetcode.com/problems/sum-of-two-integers/
public class Prob371SumOfTwoIntegers {
    public int getSum(int a, int b) {
        // Edge Case : 두 값 중 하나가 0
        if (a == 0 || b == 0) return a == 0 ? b : a;

        // Assumption : |a| >= |b|
        // why? 뺄셈 연산 시, 결과 부호를 추적하기 위해서
        int x = Math.abs(a), y = Math.abs(b);
        if (x < y) return getSum(b, a);

        // 결과 부호 : |a| >= |b| => a 부호가 결과 부호
        boolean isPlus = a > 0;

        // 뺄셈 연산 여부 : 두 숫자의 부호가 서로 다른 경우
        boolean isMinusOperation = (a > 0) ^ (b > 0);

        if (isMinusOperation) {
            // 뺄셈 연산 : 받아내림/피연산자2 비트가 0이 될때까지 반복
            int result, borrow;
            while (y != 0) {
                // 비트 빼기 : (0,0),(1,1) => 0 / (0,1),(1,0) => 1
                // => XOR : 받아내림을 고려하지 않은 뺄셈
                result = x ^ y;

                // 받아 내림 : 피연산지1 비트가 0이고, 피연산자2 비트가 1인 경우에만 발생
                // => ~x 와 y 비트가 모두 1인 경우에만 왼쪽 비트에서 1을 빌려와야 함 i.e 빼야함
                borrow = ((~x) & y) << 1;

                x = result;
                y = borrow;
            }

        } else {
            // 덧셈 연산 : 받아올림/피연산자2 비트가 0이 될때까지 반복
            int result, carry;
            while (y != 0) {
                // 비트 더하기 : (0,0),(1,1) => 0 / (0,1),(1,0) => 1
                // => XOR : 받아올림을 고려하지 않은 덧셈
                result = x ^ y;

                // 받아 올림 : 두 비트가 1인 경우에만 왼쪽 비트에 1을 더해야 함
                // => AND << 1
                carry = (x & y) << 1;

                x = result;
                y = carry;
            }
        }

        return isPlus ? x : -x;
    }
}
