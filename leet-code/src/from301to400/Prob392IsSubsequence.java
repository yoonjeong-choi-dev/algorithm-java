package from301to400;

// https://leetcode.com/problems/is-subsequence/
public class Prob392IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen > tLen) return false;

        int idx1 = 0, idx2 = 0;
        while (idx1 < sLen && idx2 < tLen) {
            if (s.charAt(idx1) == t.charAt(idx2)) {
                idx1++;
            }
            idx2++;
        }

        return idx1 == sLen;
    }
}
