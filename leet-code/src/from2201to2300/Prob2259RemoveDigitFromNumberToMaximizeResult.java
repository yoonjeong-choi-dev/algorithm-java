package from2201to2300;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/remove-digit-from-number-to-maximize-result/
public class Prob2259RemoveDigitFromNumberToMaximizeResult {
    public String removeDigit(String number, char digit) {
        int len = number.length();
        int removeIdx = 0;
        for (int i = 0; i < len; i++) {
            if (number.charAt(i) == digit) {
                removeIdx = i;
                if (i < len - 1 && digit < number.charAt(i + 1)) break;
            }
        }

        return number.substring(0, removeIdx) + number.substring(removeIdx + 1);
    }
}
