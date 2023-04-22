package from301to400;

import java.util.Stack;

// https://leetcode.com/problems/decode-string/
public class Prob394DecodeString {
    public String decodeString(String s) {
        StringBuilder curAns = new StringBuilder();

        Stack<StringBuilder> stringStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();

        StringBuilder curString;
        char curChar;
        int curCount = 0;
        for (int i = 0; i < s.length(); i++) {
            curChar = s.charAt(i);

            if (Character.isDigit(curChar)) {
                // 숫자가 나오면 (숫자들)[...] 형태가 나옴
                curCount = curCount * 10 + curChar - '0';
            } else if (curChar == '[') {
                // 현재까지 숫자 저장 및 수 초기화
                countStack.push(curCount);
                curCount = 0;

                // 현재까지 탐색한 문자열 저장 및 새로운 부분 문제를 위한 문자열 초기화
                stringStack.push(curAns);
                curAns = new StringBuilder();
            } else if (curChar == ']') {
                // 현재까지 만든 문자열을 n번 반복
                curString = stringStack.pop();
                for (int c = countStack.pop(); c > 0; c--) {
                    curString.append(curAns);
                }
                curAns = curString;
            } else {
                curAns.append(curChar);
            }
        }

        return curAns.toString();
    }
}
