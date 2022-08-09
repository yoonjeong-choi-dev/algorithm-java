package from401to500;

import java.util.Stack;

// https://leetcode.com/problems/ternary-expression-parser/
public class Prob439TernaryExpressionParser {
    public String parseTernary(String expression) {
        // ? (A) : (B) 형태에서 B, A 순서로 스택에 저장
        Stack<Integer> stack = new Stack<>();

        char curChar;
        int curExpressionIdx;
        for (int i = expression.length() - 1; i >= 0; i--) {
            // check T? vs F?
            curChar = expression.charAt(i);
            if (curChar == '?') {
                i--;
                if (expression.charAt(i) == 'T') {
                    // 앞에 표현식 사용
                    curExpressionIdx = stack.pop();
                    stack.pop();
                    stack.push(curExpressionIdx);
                } else {
                    stack.pop();
                }
            } else if (curChar != ':') stack.push(i);
        }

        return String.valueOf(expression.charAt(stack.pop()));
    }
}
