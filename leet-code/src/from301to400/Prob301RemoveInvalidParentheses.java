package from301to400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/remove-invalid-parentheses/
public class Prob301RemoveInvalidParentheses {
    private String string;
    private int len;

    private HashSet<String> ans;
    private int removedNum;


    public List<String> removeInvalidParentheses(String s) {
        return myBruteForceSolution(s);
    }

    // Solution 1 : Brute Force
    // 최대 20개의 괄호 : 해당 괄호를 삭제 여부에 따라 전체 2^20 = 1048576 = 10^6
    private List<String> myBruteForceSolution(String s) {
        string = s;
        len = s.length();
        ans = new HashSet<>();
        removedNum = Integer.MAX_VALUE;


        recursiveSearch(0, 0, 0, 0, new StringBuilder());
        return new ArrayList<>(ans);
    }

    private void recursiveSearch(int curIdx, int leftNum, int rightNum, int curRemovedNum, StringBuilder curStr) {
        // 문자의 마지막까지 도달한 경우 유효한지 확인
        if (curIdx == len) {
            // 양쪽 괄호의 숫자가 같은 경우 유효
            if (leftNum == rightNum) {
                // 현재 삭제 개수가 최소값이 경우 해당 결과가 정답
                // 같이 않은 경우 처음 찾은 경우, 같은 경우는 처음이 아닌 경우
                if (curRemovedNum <= removedNum) {
                    if (curRemovedNum < removedNum) {
                        removedNum = curRemovedNum;
                        ans.clear();
                    }
                    ans.add(new String(curStr));
                }
            }
            return;
        }

        // 재귀 호출
        char curChar = string.charAt(curIdx);
        int curStrLen = curStr.length();

        if (curChar != ')' && curChar != '(') {
            // 괄호가 아닌 경우에는 무조건 추가하고 재귀호출
            curStr.append(curChar);
            recursiveSearch(curIdx + 1, leftNum, rightNum, curRemovedNum, curStr);
            curStr.deleteCharAt(curStrLen);
        } else {

            // 현재 괄호 추가하여 탐색
            curStr.append(curChar);
            if (curChar == '(') {
                recursiveSearch(curIdx + 1, leftNum + 1, rightNum, curRemovedNum, curStr);
            } else if (leftNum > rightNum) {
                // 닫히는 괄호는 이전에 열린 괄호의 개수보다 작아야 추가 가능
                recursiveSearch(curIdx + 1, leftNum, rightNum + 1, curRemovedNum, curStr);
            }
            curStr.deleteCharAt(curStrLen);

            // 현재 괄호 삭제 후 탐색
            recursiveSearch(curIdx + 1, leftNum, rightNum, curRemovedNum + 1, curStr);

        }

    }


    public static void main(String[] args) {
        Prob301RemoveInvalidParentheses sol = new Prob301RemoveInvalidParentheses();

        String[] strings = {"()())()", "(a)())()", ")(", "()()(((("};

        String[][] ansArr = {
                {"(())()", "()()()"},
                {"(a())()", "(a)()()"},
                {""},
                {"()()"}
        };

        List<List<String>> ans = new ArrayList<>();
        for (String[] arr : ansArr) {
            ans.add(Arrays.asList(arr));
        }
        int numProblems = ansArr.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d : %s\n", i, strings[i]);
            List<String> mySol = sol.removeInvalidParentheses(strings[i]);
            if (isEqualAsSet(mySol, ans.get(i))) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans.get(i));
            }
        }
    }

    private static boolean isEqualAsSet(List<String> l1, List<String> l2) {
        HashSet<String> s1 = new HashSet<>(l1);
        HashSet<String> s2 = new HashSet<>(l2);
        return s1.equals(s2);
    }
}
