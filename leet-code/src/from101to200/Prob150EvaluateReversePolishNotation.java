package from101to200;

import java.util.Stack;

// https://leetcode.com/problems/evaluate-reverse-polish-notation/
public class Prob150EvaluateReversePolishNotation {
    // Reverse Polish notation : Posix Notation
    public int evalRPN(String[] tokens) {
        // 피연산자들을 스택에 저장
        Stack<Integer> operands = new Stack<>();
        int num1, num2;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    num2 = operands.pop();
                    num1 = operands.pop();
                    operands.push(num1 + num2);
                    break;
                case "-":
                    num2 = operands.pop();
                    num1 = operands.pop();
                    operands.push(num1 - num2);
                    break;
                case "*":
                    num2 = operands.pop();
                    num1 = operands.pop();
                    operands.push(num1 * num2);
                    break;
                case "/":
                    num2 = operands.pop();
                    num1 = operands.pop();
                    operands.push(num1 / num2);
                    break;
                default:
                    operands.push(Integer.valueOf(token));
                    break;
            }
        }

        return operands.pop();
    }

    public static void main(String[] args) {
        Prob150EvaluateReversePolishNotation sol = new Prob150EvaluateReversePolishNotation();
        String[][] tokens = {
                {"2", "1", "+", "3", "*"},
                {"4", "13", "5", "/", "+"},
                {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}
        };

        int[] ans = {9, 6, 22};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.evalRPN(tokens[i]);
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
