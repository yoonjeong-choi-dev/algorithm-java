package from601to700;

import java.util.Stack;

// https://leetcode.com/problems/baseball-game/
public class Prob682BaseballGame {
    public int calPoints(String[] ops) {
        // 계산 결과를 스택에 저장
        Stack<Integer> stack = new Stack<>();
        int num1, num2;
        for (String op : ops) {
            if (op.equals("C")) {
                stack.pop();
            } else if (op.equals("D")) {
                stack.push(stack.peek() * 2);
            } else if (op.equals("+")) {
                num1 = stack.pop();
                num2 = stack.peek();

                stack.push(num1);
                stack.push(num1 + num2);
            } else {
                // 나머지 경우 : 숫자 => 스택에 저장
                stack.push(Integer.valueOf(op));
            }
        }

        num1 = 0;
        while (!stack.isEmpty()) {
            num1 += stack.pop();
        }
        return num1;
    }

    public static void main(String[] args) {
        Prob682BaseballGame sol = new Prob682BaseballGame();
        sol.calPoints(new String[] {"5","2","C","D","+"});
    }
}
