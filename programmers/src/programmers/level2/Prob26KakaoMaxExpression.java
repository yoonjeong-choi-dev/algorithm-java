package programmers.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// https://programmers.co.kr/learn/courses/30/lessons/67257
public class Prob26KakaoMaxExpression {
    HashMap<String, Integer> opsSet;
    List<String> tokens;

    public long solution(String expression) {
        opsSet = new HashMap<>(3);
        tokens = new ArrayList<>();

        Pattern pattern = Pattern.compile("[\\-*+]");
        Matcher matcher = pattern.matcher(expression);

        int start = 0, opsIdx;
        while (matcher.find()) {
            opsIdx = matcher.start();
            opsSet.put(expression.substring(opsIdx, opsIdx + 1), -1);
            tokens.add(expression.substring(start, opsIdx));
            tokens.add(expression.substring(opsIdx, opsIdx + 1));

            start = opsIdx + 1;
        }
        tokens.add(expression.substring(start));


        return permutate(0);
    }

    // 연산자들의 우선순위에 대한 permutation
    private long permutate(int step) {
        // 모든 연산자에 대해서 순위가 결정된 경우
        if(step == opsSet.size()) {
            return Math.abs(calculatePostFix(convertToPostfix(tokens)));
        }

        // 현재 우선순위(step)를 할당할 연산자 지정
        long ans = 0L;
        for(String op : opsSet.keySet()) {
            if(opsSet.get(op) == -1) {
                opsSet.put(op, step);
                ans = Math.max(ans, permutate(step+1));
                opsSet.put(op, -1);
            }
        }
        return ans;
    }

    // 중위 표현식 -> 후위 표현식 변경
    private List<String> convertToPostfix(List<String> tokens) {
        List<String> ret = new ArrayList<>(tokens.size());

        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            // 피연산자인 경우 바로 push
            if (!opsSet.containsKey(token)) {
                ret.add(token);
                continue;
            }

            // 연산자의 경우 스택에 있는 연산자와 우선순위 비교
            // stack top 연산자 우선순위 < 현재 연산자 될 떄까지 pop
            while (!stack.empty() && (opsSet.get(stack.peek()) >= opsSet.get(token))) {
                ret.add(stack.pop());
            }
            stack.push(token);
        }

        // 나머지 push
        while (!stack.empty()) ret.add(stack.pop());
        return ret;
    }

    // 후위 표현식 계산
    private Long calculatePostFix(List<String> postFix) {
        Stack<Long> stack = new Stack<>();

        for (String token : postFix) {
            if (!opsSet.containsKey(token)) {
                // 연산자면 스택에 넣는다
                stack.push(Long.parseLong(token));
            } else {
                // 연산자면 계산
                Long p2 = stack.pop();
                Long p1 = stack.pop();

                if (token.equals("+")) {
                    stack.push(p1 + p2);
                } else if (token.equals("-")) {
                    stack.push(p1 - p2);
                } else {
                    stack.push(p1 * p2);
                }
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        Prob26KakaoMaxExpression sol = new Prob26KakaoMaxExpression();

        String[] expr = {"100-200*300-500+20", "50*6-3*2"};
        long[] ans = {60420, 300};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================= Problem %d\n", i);
            long mySol = sol.solution(expr[i]);
            if (ans[i] == mySol) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
