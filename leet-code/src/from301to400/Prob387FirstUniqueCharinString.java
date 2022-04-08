package from301to400;

// https://leetcode.com/problems/first-unique-character-in-a-string/
public class Prob387FirstUniqueCharinString {
    public int firstUniqChar(String s) {
        int len = s.length();

        // s consists of only lowercase English letters.
        int[] numOccurs = new int[26];
        for (int i = 0; i < len; i++) {
            numOccurs[s.charAt(i) - 'a']++;
        }

        for(int i=0;i<len;i++){
            if(numOccurs[s.charAt(i)-'a'] == 1) return i;
        }

        return  -1;
    }
}
