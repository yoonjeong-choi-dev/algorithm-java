package from401to500;

// https://leetcode.com/problems/valid-word-abbreviation/
public class Prob408ValidWordAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
        int len1 = word.length(), len2 = abbr.length();
        int idx1 = 0, idx2 = 0;

        char c1, c2;
        int count;
        while (idx1 < len1 && idx2 < len2) {

            c1 = word.charAt(idx1);
            c2 = abbr.charAt(idx2);

            if (c1 == c2) {
                idx1++;
                idx2++;
                continue;
            }

            // c2 must be digit
            if (c2 <= '0' || c2 > '9') return false;

            count = 0;
            while (idx2 < len2 && Character.isDigit(abbr.charAt(idx2))) {
                count = count * 10 + abbr.charAt(idx2++) - '0';
            }

            // word 는 현재 인덱스부터 count 만큼 무시되어야 함
            idx1 += count;
        }

        return idx1 == len1 && idx2 == len2;
    }
}
