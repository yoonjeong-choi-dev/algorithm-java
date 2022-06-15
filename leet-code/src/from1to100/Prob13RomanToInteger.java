package from1to100;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/roman-to-integer/
public class Prob13RomanToInteger {
    // Roman to Integer Map
    private static final Map<Character, Integer> romanToInt = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt(String s) {
        int len = s.length();
        int idx = 0;

        int ans = 0;
        int curVal, nextVal;
        while (idx < len) {
            curVal = romanToInt.get(s.charAt(idx));

            // Check Special Rule
            if (idx < len - 1) {
                nextVal = romanToInt.get(s.charAt(idx + 1));
            } else {
                nextVal = -1;
            }

            // Roman numerals are usually written largest to smallest from left to right.
            // => special Rule 인 경우에는 (현재 숫자 < 다음 숫자)
            if (curVal < nextVal) {
                ans += nextVal - curVal;
                idx += 2;
            } else {
                ans += curVal;
                idx += 1;
            }
        }

        return ans;
    }
}
