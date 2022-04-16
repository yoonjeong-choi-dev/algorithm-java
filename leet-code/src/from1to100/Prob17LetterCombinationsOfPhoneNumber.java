package from1to100;

import java.util.*;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
public class Prob17LetterCombinationsOfPhoneNumber {
    // 문제에 필요한 상수
    static final Map<Character, String> numToChar = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    // 재귀 호출에 필요한 변수들
    int len;
    String str;
    List<String> ans;
    char[] arr;

    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) return new ArrayList<>();

        str = digits;
        len = digits.length();
        int totalSize = 1;
        for (int i = 0; i < len; i++) {
            totalSize *= numToChar.get(digits.charAt(i)).length();
        }

        ans = new ArrayList<>(totalSize);
        arr = new char[len];

        recursive(0);
        return ans;
    }

    private void recursive(int curIdx) {
        if (curIdx == len) {
            ans.add(new String(arr));
            return;
        }

        // 현재 숫자에 대해서 가능한 문자들 탐색
        String curChars = numToChar.get(str.charAt(curIdx));
        for (int i = 0; i < curChars.length(); i++) {
            arr[curIdx] = curChars.charAt(i);
            recursive(curIdx + 1);
        }
    }

    public static void main(String[] args) {
        Prob17LetterCombinationsOfPhoneNumber sol = new Prob17LetterCombinationsOfPhoneNumber();

        String[] digits = {"23", "", "2"};

        String[][] ans = {
                {"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"},
                {},
                {"a", "b", "c"}
        };
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            String[] mySol = sol.letterCombinations(digits[i]).toArray(new String[0]);
            if (Arrays.deepEquals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.deepToString(mySol));
                System.out.println("Expected : " + Arrays.deepToString(ans[i]));
            }
        }
    }
}
