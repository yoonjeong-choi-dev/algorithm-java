package from1to100;

// https://leetcode.com/problems/count-and-say/
public class Prob38CountAndSay {
    public String countAndSay(int n) {
        if (n == 1) return "1";

        String prev = countAndSay(n - 1);
        StringBuilder ans = new StringBuilder();

        int curIdx = 0;
        int len = prev.length();

        char curChar;
        int count;
        while (curIdx < len) {
            curChar = prev.charAt(curIdx);
            count = 0;

            while (curIdx < len && curChar == prev.charAt(curIdx)) {
                count++;
                curIdx++;
            }

            ans.append(String.valueOf(count)).append(curChar);
        }

        return ans.toString();
    }
}
