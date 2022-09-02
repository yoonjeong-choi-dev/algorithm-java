package from201to300;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/basic-calculator/
public class Prob224BasicCalculator {

    class Node {
        boolean isOperator;
        int number, priority;
        char operator;

        Node(char c) {
            operator = c;
            isOperator = true;
            priority = getOpsPriority(c);
        }

        Node(int number) {
            this.number = number;
            isOperator = false;
        }
    }

    public int calculate(String s) {
        List<Node> postfix = getPostfixExpr(s);
        Stack<Integer> stack = new Stack<>();

        char curOps;
        int left, right;
        for (Node node : postfix) {
            if (node.isOperator) {
                curOps = node.operator;
                right = stack.pop();
                left = stack.pop();

                if (curOps == '+') {
                    stack.push(left + right);
                } else if (curOps == '-') {
                    stack.push(left - right);
                } else if (curOps == '*') {
                    stack.push(left * right);
                } else if (curOps == '/') {
                    stack.push(left / right);
                }
            } else {
                stack.push(node.number);
            }
        }

        return stack.pop();
    }

    private List<Node> getPostfixExpr(String s) {
        List<Node> ret = new ArrayList<>();
        Stack<Node> operators = new Stack<>();

        int operand = 0;
        boolean seenDigit = false;

        // unary operation 인 경우 i.e 음수
        // => -1 을 곱하는 것과 동일
        int startIdx =  0;
        if(s.charAt(0) == '-') {
            startIdx = 1;
            ret.add(new Node(-1));
            operators.push(new Node('*'));
        }

        char c;
        for (int i = startIdx; i < s.length(); i++) {
            c = s.charAt(i);

            if (Character.isWhitespace(c)) continue;

            if (Character.isDigit(c)) {
                operand = operand * 10 + (c - '0');
                seenDigit = true;
            } else if (c == '(') {
                operators.push(new Node(c));

                // unary operation 인 경우 i.e 음수
                if (s.charAt(i + 1) == '-') {
                    ret.add(new Node(-1));
                    operators.push(new Node('*'));
                    i++;
                }
            } else if (c == ')') {
                // 현재까지 계산된 숫자 저장
                if(seenDigit) {
                    ret.add(new Node(operand));
                    operand = 0;
                    seenDigit = false;
                }

                // ( 이후에 저장된 연산자들 저장
                while (operators.peek().operator != '(') ret.add(operators.pop());

                // ( 제거
                operators.pop();
            } else {
                // 현재까지 계산된 숫자 저장
                if(seenDigit) {
                    ret.add(new Node(operand));
                    operand = 0;
                    seenDigit = false;
                }

                // 연산자 우선 순위 고려하여 저장
                // => 스택에 있는 연산자의 우선 순위는 strictly increasing
                int curPriority = getOpsPriority(c);
                while (!operators.isEmpty() && curPriority <= operators.peek().priority) ret.add(operators.pop());

                operators.push(new Node(c));
            }

        }

        // 마지막 숫자 저장
        if(seenDigit) ret.add(new Node(operand));
        while (!operators.isEmpty()) ret.add(operators.pop());
        return ret;
    }

    private int getOpsPriority(char c) {
        if (c == '(' || c == ')') return 0;
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        return -1;
    }

}
