package from1to100;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/integer-to-roman/
public class Prob12IntegerToRoman {

    private static final int[] keys = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int num) {
        int numKeys = keys.length;

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < numKeys && num > 0; i++) {
            // 현재 키로 로만 문자 만들기
            int curKey = keys[i];
            while (num >= curKey) {
                num -= curKey;
                ans.append(romans[i]);
            }
        }


        return ans.toString();
    }
}
