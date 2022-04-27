package from1201to1300;

import java.util.*;

// https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
public class Prob1249MinimumRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        int len = s.length();
        Set<Integer> removeIdx = new HashSet<>();
        Stack<Integer> leftIndex = new Stack<>();

        char curChar;
        for (int i = 0; i < len; i++) {
            curChar = s.charAt(i);
            if (curChar == '(') {
                leftIndex.push(i);
            } else if (curChar == ')') {
                // 왼쪽 괄호가 없는 경우 해당 오른쪽 괄호 삭제 필요
                if (leftIndex.isEmpty()) removeIdx.add(i);
                else leftIndex.pop();
            }
        }

        // 나머지 왼쪽 괄호 삭제
        while (!leftIndex.isEmpty()) removeIdx.add(leftIndex.pop());

        StringBuilder ans = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            if (!removeIdx.contains(i)) {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }
}
