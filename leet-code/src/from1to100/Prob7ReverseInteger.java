package from1to100;

// https://leetcode.com/problems/reverse-integer/
public class Prob7ReverseInteger {
    // Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
    public int reverse(int x) {
        // 2147483647 -> 214748364
        final int UPPER_BOUND = Integer.MAX_VALUE / 10;

        // -2147483648 -> -214748364
        final int LOWER_BOUND = Integer.MIN_VALUE / 10;

        int ans = 0;
        int curDigit;
        while (x != 0) {
            // 음수의 경우에도 % 및 / 연산의 결과가 음수로 유지됨
            curDigit = x % 10;
            x /= 10;

            // ans <- ans * 10 + curDigit 이 Integer 범위에 속하는지 확인
            if (ans > UPPER_BOUND || (ans == UPPER_BOUND && curDigit > 7)) {
                // 2147483647 을 초과하면 스택 오버플로우
                // ans > 214748364 or ans == 214748364 &&  curDigit > 7
                return 0;
            }

            if(ans < LOWER_BOUND || (ans == LOWER_BOUND && curDigit < -8)) {
                // -2147483648 보다 작은 값이면 스택 오버플로우
                // ans < -214748364 or ans == -214748364 && curDigit < -8
                return 0;
            }

            // Reverse Digit
            ans = 10 * ans + curDigit;
        }

        return ans;
    }
}
