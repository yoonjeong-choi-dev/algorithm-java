package from1001to1100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/confusing-number/
public class Prob1056ConfusingNumber {

    private static final Map<Integer, Integer> confused = new HashMap<Integer, Integer>() {{
        put(0, 0);
        put(1, 1);
        put(6, 9);
        put(8, 8);
        put(9, 6);
    }};

    public boolean confusingNumber(int n) {
        // 0 <= n <= 10^ 9
        List<Integer> digits = new ArrayList<>();
        while (n != 0) {
            digits.add(n % 10);
            n /= 10;
        }

        int digitLen = digits.size();
        int[] converted = new int[digitLen];
        for (int i = 0; i < digitLen; i++) {
            if (!confused.containsKey(digits.get(i))) return false;
            converted[digitLen - 1 - i] = confused.get(digits.get(i));
        }

        boolean isSame = true;
        for (int i = 0; i < digits.size(); i++) {
            if (!digits.get(i).equals(converted[i])) {
                isSame = false;
                break;
            }
        }

        return !isSame;
    }
}
