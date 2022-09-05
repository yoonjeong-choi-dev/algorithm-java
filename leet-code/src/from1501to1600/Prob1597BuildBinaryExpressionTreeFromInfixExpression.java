package from1501to1600;

import java.util.Stack;

// https://leetcode.com/problems/build-binary-expression-tree-from-infix-expression/
public class Prob1597BuildBinaryExpressionTreeFromInfixExpression {
    class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public Node expTree(String s) {
        String postfix = getPostfixExpr(s);
        Stack<Node> stack = new Stack<>();

        for (char c : postfix.toCharArray()) {
            if (Character.isDigit(c)) stack.push(new Node(c));
            else {
                Node operator = new Node(c);
                operator.right = stack.pop();
                operator.left = stack.pop();
                stack.push(operator);
            }
        }

        return stack.pop();
    }

    private String getPostfixExpr(String s) {
        StringBuilder ret = new StringBuilder(s.length());
        Stack<Character> operators = new Stack<>();

        for (char c : s.toCharArray()) {
            // Operands in s are exactly 1 digit
            if (Character.isDigit(c)) {
                ret.append(c);
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                // ( 이후 저장된 연산자들 pop
                while (operators.peek() != '(') ret.append(operators.pop());
                operators.pop();
            } else {
                // 연산자 우선순위 고려하여 저장
                // => 스택에는 연산자 우선순위에 대해서 strictly 오름차순
                int curPriority = getOpsPriority(c);
                while (!operators.isEmpty() && curPriority <= getOpsPriority(operators.peek()))
                    ret.append(operators.pop());

                operators.push(c);
            }
        }

        while (!operators.isEmpty()) ret.append(operators.pop());

        return ret.toString();
    }

    private int getOpsPriority(char c) {
        if (c == '(' || c == ')') return 0;
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        return -1;
    }
}
