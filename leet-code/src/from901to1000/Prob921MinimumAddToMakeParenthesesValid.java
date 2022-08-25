package from901to1000;

// https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
public class Prob921MinimumAddToMakeParenthesesValid {
    public int minAddToMakeValid(String s) {
        int ans = 0;
        int leftNum = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') leftNum++;
            else {
                if (leftNum == 0) {
                    // add '('
                    ans++;
                } else {
                    leftNum--;
                }
            }
        }

        // add ')'
        ans += leftNum;
        return ans;
    }
}
