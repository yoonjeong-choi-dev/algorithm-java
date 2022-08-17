package from201to300;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/strobogrammatic-number/
public class Prob246StrobogrammaticNumber {

    private static final Map<Character, Character> strobogrammaticMap = new HashMap<Character, Character>() {{
        put('0', '0');
        put('1', '1');
        put('6', '9');
        put('8', '8');
        put('9', '6');
    }};

    public boolean isStrobogrammatic(String num) {

        int left = 0, right = num.length() - 1;
        while (left <= right) {
            if(!strobogrammaticMap.containsKey(num.charAt(left)) ||
                    strobogrammaticMap.get(num.charAt(left)) != num.charAt(right)) return false;
            left++;
            right--;
        }

        return true;
    }
}
