package from1to100;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/generate-parentheses/
public class Prob22GenerateParentheses {
    List<String> ans;
    StringBuilder curStr;
    int len;
    int n;

    public List<String> generateParenthesis(int n) {
        this.n = n;
        len = n * 2;
        ans = new LinkedList<>();
        curStr = new StringBuilder(len);
        for (int i = 0; i < len; i++) curStr.append("(");


        recursive(0, 0, 0);
        return ans;
    }

    private void recursive(int idx, int leftRemain, int leftNum) {
        if (idx == len) {
            if (leftRemain == 0) {
                ans.add(curStr.toString());
            }
            return;
        }

        // 왼쪽 괄호를 추가 가능한 경우
        if (leftNum < n) {
            curStr.setCharAt(idx, '(');
            recursive(idx + 1, leftRemain + 1, leftNum + 1);
        }

        // 오른쪽 괄호를 추가 가능한 경우
        if (leftRemain > 0) {
            curStr.setCharAt(idx, ')');
            recursive(idx + 1, leftRemain - 1, leftNum);
        }
    }
}
