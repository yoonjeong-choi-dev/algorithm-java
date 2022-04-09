package from1to100;

import java.util.HashMap;
import java.util.Stack;

// https://leetcode.com/problems/valid-parentheses/
public class Prob20ValidParentheses {
    public boolean isValid(String s) {
        HashMap<Character, Character> rightToLeft = new HashMap<>();
        rightToLeft.put(')', '(');
        rightToLeft.put('}', '{');
        rightToLeft.put(']', '[');

        Stack<Character> left = new Stack<>();

        char curChar;
        for (int i = 0; i < s.length(); i++) {
            curChar = s.charAt(i);
            if (curChar == '(' || curChar == '[' || curChar == '{') {
                // 왼쪽 괄호는 스택에 저장
                left.push(curChar);
            } else {
                // 스택의 탑과 오른쪽 괄호 비교
                if (left.isEmpty() || rightToLeft.get(curChar) != left.peek()) return false;

                left.pop();
            }
        }

        return left.isEmpty();
    }

    public static void main(String[] args) {
        Prob20ValidParentheses sol = new Prob20ValidParentheses();

        String[] strings = {"()", "()[]{}", "(]"};

        boolean[] ans = {true, true, false};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            boolean mySol = sol.isValid(strings[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
