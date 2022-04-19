package from1to100;

// https://leetcode.com/problems/length-of-last-word/
public class Prob58LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        int curIdx = s.length() - 1;
        while(curIdx>=0 && s.charAt(curIdx) == ' ') curIdx--;

        int ans = 0;
        while(curIdx>= 0&& s.charAt(curIdx) != ' ') {
            ans++;
            curIdx--;
        }
        return ans;
    }
}
