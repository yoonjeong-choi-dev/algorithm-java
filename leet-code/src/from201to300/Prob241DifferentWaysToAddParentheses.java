package from201to300;

import java.util.*;

// https://leetcode.com/problems/different-ways-to-add-parentheses/
public class Prob241DifferentWaysToAddParentheses {

    class Pair {
        int start, end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return start == pair.start && end == pair.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    private String expression;
    private Map<Pair, List<Integer>> memo;


    public List<Integer> diffWaysToCompute(String expression) {
        this.expression = expression;
        memo = new HashMap<>();
        return recursive(new Pair(0, expression.length()));
    }

    private List<Integer> recursive(Pair interval) {
        List<Integer> ret = memo.get(interval);
        if (ret != null) return ret;

        ret = new ArrayList<>();

        Pair first, second;
        List<Integer> firstAns, secondAns;
        char curChar;
        for (int i = interval.start; i < interval.end; i++) {
            curChar = expression.charAt(i);

            // 오퍼레이션 인 경우, 두 개로 쪼개서 계산
            if (curChar == '+' || curChar == '-' || curChar == '*') {
                first = new Pair(interval.start, i);
                second = new Pair(i + 1, interval.end);

                firstAns = recursive(first);
                secondAns = recursive(second);

                for (int r1 : firstAns) {
                    for (int r2 : secondAns) {
                        switch (curChar) {
                            case '+':
                                ret.add(r1 + r2);
                                break;
                            case '-':
                                ret.add(r1 - r2);
                                break;
                            case '*':
                                ret.add(r1 * r2);
                                break;
                        }
                    }
                }
            }
        }

        // 현재 문자열이 숫자인 경우
        if (ret.isEmpty()) {
            int curNum = 0;
            for (int i = interval.start; i < interval.end; i++) {
                curNum = curNum * 10 + expression.charAt(i) - '0';
            }
            ret.add(curNum);
        }

        memo.put(interval, ret);
        return ret;
    }
}
