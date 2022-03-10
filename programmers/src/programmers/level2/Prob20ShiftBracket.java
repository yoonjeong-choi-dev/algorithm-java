package programmers.level2;

import java.util.Stack;

public class Prob20ShiftBracket {
    public int solution(String s) {
        int len = s.length();
        int answer = 0;

        // 각 괄호 유형의 왼쪽 괄호 개수
        Stack<Character> stack = new Stack<>();
        boolean isValid;

        for (int shift = 0; shift < len - 1; shift++) {
            // shift 만큼 문자열을 옮겨서 계산
            stack.clear();
            isValid = true;
            for (int i = 0; i < len; i++) {
                char curChar = s.charAt((shift + i) % len);
                if (curChar == '(' || curChar == '{' || curChar == '[') stack.push(curChar);
                else {
                    if (stack.isEmpty() || stack.peek() != opposite(curChar)) {
                        isValid = false;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }

            if (isValid && stack.isEmpty()) answer++;
        }


        return answer;
    }

    private char opposite(char c) {
        if (c == ')') return '(';
        else if (c == ']') return '[';
        else return '{';
    }

    public static void main(String[] args) {
        Prob20ShiftBracket sol = new Prob20ShiftBracket();

        String[] strings = {"[](){}", "}]()[{", "[)(]", "}}}", "([{)}]"};

        int[] ans = {3, 2, 0, 0, 0};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            int mySol = sol.solution(strings[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
