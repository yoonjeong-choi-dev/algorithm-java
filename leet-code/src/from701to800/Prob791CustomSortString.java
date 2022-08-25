package from701to800;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/custom-sort-string/
public class Prob791CustomSortString {
    public String customSortString(String order, String s) {
        // order and s consist of lowercase English letters.
        int[] priority = new int[26];

        // order 에 없는 문자들은 무조건 우선순위가 앞으로
        Arrays.fill(priority, -1);
        for (int i = 0; i < order.length(); i++) priority[order.charAt(i) - 'a'] = i;

        Character[] array = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) array[i] = s.charAt(i);

        Arrays.sort(array, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return priority[o1 - 'a'] - priority[o2 - 'a'];
            }
        });

        StringBuilder sb = new StringBuilder(array.length);
        for(Character c : array) sb.append(c);
        return sb.toString();
    }
}
