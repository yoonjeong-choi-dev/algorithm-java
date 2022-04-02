package from601to700;

// https://leetcode.com/problems/valid-palindrome-ii/
public class Prob680ValidPalindrome2 {

    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                break;
            }
        }

        // 문자열이 회문인 경우
        if (left >= right) return true;

        // 왼쪽 문자 제거해서 탐색
        boolean isPalindrome = true;
        int left1 = left + 1, right1 = right;
        while (left1 < right1) {
            if (s.charAt(left1) != s.charAt(right1)) {
                isPalindrome = false;
                break;
            }
            left1++;
            right1--;
        }

        if (isPalindrome) return true;

        // 오른쪽 문자 제거해서 탐색
        isPalindrome = true;
        left1 = left;
        right1 = right - 1;
        while (left1 < right1) {
            if (s.charAt(left1) != s.charAt(right1)) {
                isPalindrome = false;
                break;
            }
            left1++;
            right1--;
        }

        return isPalindrome;
    }

    public static void main(String[] args) {
        Prob680ValidPalindrome2 sol = new Prob680ValidPalindrome2();

        String[] strings = {"aba", "abca", "abc"};

        boolean[] ans = {true, true, false};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            boolean mySol = sol.validPalindrome(strings[i]);
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
