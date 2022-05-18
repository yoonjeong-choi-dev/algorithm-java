package from401to500;

import java.util.Stack;

// https://leetcode.com/problems/remove-k-digits/
public class Prob402RemoveKDigits {
    public String removeKdigits(String num, int k) {
        int len = num.length();
        int ansLen = len - k;

        Stack<Character> stack = new Stack<>();

        char curChar;
        int i = 0;
        for (; i < len; i++) {
            curChar = num.charAt(i);

            if (stack.isEmpty()) {
                // 스택이 비어 있는 경우에는 무조건 저장
                stack.push(num.charAt(i));
                continue;
            }

            // 현재 숫자보다 큰 숫자들 삭제
            while (!stack.isEmpty() && k > 0 && stack.peek() > curChar) {
                stack.pop();
                k--;
            }

            if (k == 0) break;
            stack.push(curChar);
        }

        // 정답 길이보다 스택에 더 많은 데이터가 있는 경우 뒷부분들 삭제
        // : 스택은 오름차순으로 저장되어 있으므로
        while (!stack.isEmpty() && stack.size() > ansLen) {
            stack.pop();
        }

        StringBuilder ans = new StringBuilder(len - k);

        // 스택에 있는 숫자들 저장
        while (!stack.isEmpty()) {
            ans.insert(0, stack.pop());
        }

        // 반복문을 조기 탈출한 경우, 뒷부분은 무조건 추가
        if (i != len) ans.append(num.substring(i));

        // 앞에 있는 0들 제거
        while (ans.length() != 0 && ans.charAt(0) == '0') ans.deleteCharAt(0);

        return ans.length() == 0 ? "0" : ans.toString();
    }
}
