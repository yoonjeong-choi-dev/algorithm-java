package from1to100;

// https://leetcode.com/problems/string-to-integer-atoi/
public class Prob8StringToInteger {

    // Ref : Problem 7
    // => int 범위 내에서 변환해야 함
    public int myAtoi(String s) {
        // 2147483647 -> 214748364
        final int UPPER_BOUND = Integer.MAX_VALUE / 10;

        // -2147483648 -> -214748364
        final int LOWER_BOUND = Integer.MIN_VALUE / 10;

        // Step 1 : ignore leading whitespaces
        int len = s.length();
        int idx = 0;
        while (idx < len && s.charAt(idx) == ' ') idx++;

        // If no digits were read, then the integer is 0
        if (idx == len) return 0;

        boolean isNegative = false;
        if (s.charAt(idx) == '-') {
            idx++;
            isNegative = true;
        } else if(s.charAt(idx) == '+') {
            idx++;
        }

        int ans = 0;
        int curDigit;
        int curChar;

        for (; idx < len; idx++) {
            curChar = s.charAt(idx);
            if (curChar < '0' || curChar > '9') break;

            curDigit = curChar - '0';
            if (isNegative) curDigit *= -1;

            // Clamping
            if (isNegative) {
                if (ans < LOWER_BOUND || (ans == LOWER_BOUND && curDigit < -8)) return Integer.MIN_VALUE;
            } else {
                if (ans > UPPER_BOUND || (ans == UPPER_BOUND && curDigit > 7)) return Integer.MAX_VALUE;
            }

            ans = ans * 10 + curDigit;
        }


        return ans;
    }
}
