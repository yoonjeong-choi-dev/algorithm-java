package from1701to1800;

// https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/
public class Prob1790SwapToMakeStringsEqual {
    public boolean areAlmostEqual(String s1, String s2) {

        // 문자가 다른 인덱스 저장(최대 2개)
        int numDiff = 0;
        int[] diffIndex = new int[2];

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                // 다른 문자가 3개 이상인 경우 불가능
                if (numDiff == 2) return false;
                diffIndex[numDiff++] = i;
            }
        }

        if (numDiff == 0) return true;
        if (numDiff == 1) return false;

        return s1.charAt(diffIndex[0]) == s2.charAt(diffIndex[1]) && s1.charAt(diffIndex[1]) == s2.charAt(diffIndex[0]);
    }
}
