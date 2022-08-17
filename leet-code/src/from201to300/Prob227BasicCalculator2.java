package from201to300;

import java.util.Stack;

// https://leetcode.com/problems/basic-calculator-ii/
public class Prob227BasicCalculator2 {
    public int calculate(String s) {
        int len = s.length();
        int lastIndex = len - 1;

        // + 피연산자들만 저장
        Stack<Integer> stack = new Stack<>();

        int number = 0;
        char operation = '+', curChar;
        for (int i = 0; i < len; i++) {
            curChar = s.charAt(i);

            if (Character.isDigit(curChar)) {
                number = number * 10 + curChar - '0';
            }

            // 마지막 숫자 처리를 위해
            if (curChar != ' ' && !Character.isDigit(curChar) || i == lastIndex) {
                if (operation == '+') stack.push(number);
                else if (operation == '-') stack.push(-number);
                else if (operation == '*') stack.push(stack.pop() * number);
                else if (operation == '/') stack.push(stack.pop() / number);

                operation = curChar;
                number = 0;
            }
        }

        int ans = 0;
        while (!stack.isEmpty()) ans += stack.pop();

        return ans;
    }
}
