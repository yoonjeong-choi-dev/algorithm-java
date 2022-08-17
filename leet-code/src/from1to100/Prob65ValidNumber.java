package from1to100;

// https://leetcode.com/problems/valid-number/
public class Prob65ValidNumber {
    public boolean isNumber(String s) {
        // . 및 e/E 는 반드시 한번만 나와야 함
        boolean seenDot = false, seenExp = false;

        // 문자열에는 반드시 숫자가 하나는 있어야 함
        boolean hasDigit = false;

        char cur;
        for (int i = 0; i < s.length(); i++) {
            cur = s.charAt(i);

            if (Character.isDigit(cur)) {
                hasDigit = true;
            } else if (cur == '+' || cur == '-') {
                // 부호는 처음이나 e/E 바로 직후에 나와야 함
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') return false;
            } else if (cur == 'E' || cur == 'e') {
                // e/E 는 한번만 나와야하고, 그전에 숫자가 등장해야 함
                if (seenExp || !hasDigit) return false;
                seenExp = true;

                // e/E 뒤에 숫자가 다시 발견되어야 함
                hasDigit = false;
            } else if (cur == '.') {
                // . 는 한번만 등장해야 하고, e/E 이후에는 나오면 안됨
                if (seenDot || seenExp) return false;
                seenDot = true;
            } else {
                return false;
            }
        }

        return hasDigit;
    }
}
