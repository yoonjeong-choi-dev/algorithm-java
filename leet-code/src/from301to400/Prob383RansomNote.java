package from301to400;

// https://leetcode.com/problems/ransom-note/
public class Prob383RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        // magazine consists of only lowercase English letters.
        int[] numOccurs = new int[26];
        for(int i=magazine.length()-1;i>=0;i--){
            numOccurs[magazine.charAt(i) - 'a']++;
        }

        int curIdx;
        for(int i=ransomNote.length()-1;i>=0;i--){
            curIdx = ransomNote.charAt(i) - 'a';
            numOccurs[curIdx]--;
            if(numOccurs[curIdx] == -1) return false;
        }

        return true;
    }
}
