package from101to200;

// https://leetcode.com/problems/one-edit-distance/
public class Prob161OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();

        if (len1 == len2) return replace(s, t);
        else if (len1 == len2 + 1) return insert(t, s);
        else if (len1 + 1 == len2) return insert(s, t);
        else return false;
    }

    private boolean replace(String s, String t) {
        int numDiff = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                numDiff++;

                if (numDiff > 1) return false;
            }
        }
        return numDiff == 1;
    }

    private boolean insert(String s, String t) {
        // s.length + 1 == t.length
        // => s 에 삽입
        int len1 = s.length();
        int len2 = t.length();

        boolean isInserted = false;
        int p1 = 0, p2 = 0;
        while (p1 < len1 && p2 < len2) {
            if(s.charAt(p1) == t.charAt(p2)) {
                p1++;
                p2++;
            } else {
                if(isInserted) return false;
                isInserted = true;
                p2++;
            }
        }
        return true;
    }
}
