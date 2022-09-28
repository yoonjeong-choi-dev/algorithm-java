package from201to300;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/isomorphic-strings/
public class Prob205IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        int len = s.length();

        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();

        char c1, c2;
        for (int i = 0; i < len; i++) {
            c1 = s.charAt(i);
            c2 = t.charAt(i);

            if (map1.containsKey(c1) ^ map2.containsKey(c2)) return false;
            else if (map1.containsKey(c1)) {
                if (map1.get(c1) != c2 || map2.get(c2) != c1) return false;
            } else {
                map1.put(c1, c2);
                map2.put(c2, c1);
            }
        }
        return true;
    }
}
