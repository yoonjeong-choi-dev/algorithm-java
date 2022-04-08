package from201to300;

// https://leetcode.com/problems/valid-anagram/
public class Prob242ValidAnagram {
    public boolean isAnagram(String s, String t) {
        int len = s.length();

        if(len != t.length()) return false;

        //s and t consist of lowercase English letters.
        int[] numOccurs = new int[26];
        for(int i=0;i<len;i++){
            numOccurs[s.charAt(i) - 'a']++;
        }

        int curIdx;
        for(int i=0;i<len;i++){
            curIdx = t.charAt(i) - 'a';
            numOccurs[curIdx]--;
            if(numOccurs[curIdx] == -1) return false;
        }

        return true;
    }
}
