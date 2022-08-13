package from1001to1100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/brace-expansion/
public class Prob1087BraceExpansion {

    private List<String> ans;
    private List<List<Character>> braces;

    public String[] expand(String s) {
        braces = new ArrayList<>();
        List<Character> brace;

        int idx = 0, len = s.length();
        while (idx < len) {
            brace = new ArrayList<>();
            if (s.charAt(idx) == '{') {
                idx++;
                while (s.charAt(idx) != '}') {
                    if (s.charAt(idx) != ',') brace.add(s.charAt(idx));
                    idx++;
                }
                Collections.sort(brace);
            } else {
                brace.add(s.charAt(idx));
            }

            braces.add(brace);
            idx++;
        }

        ans = new ArrayList<>();
        char[] path = new char[braces.size()];
        recursive(0, path);

        return ans.toArray(new String[0]);
    }

    private void recursive(int idx, char[] path) {
        if (idx == path.length) {
            ans.add(new String(path));
            return;
        }

        for (Character c : braces.get(idx)) {
            path[idx] = c;
            recursive(idx + 1, path);
        }
    }

    public static void main(String[] args) {
        Prob1087BraceExpansion sol = new Prob1087BraceExpansion();
        sol.expand("{a,b}c{d,e}f");
        sol.expand("abcd");
    }

}
