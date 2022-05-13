package from201to300;

// https://leetcode.com/problems/shortest-palindrome/
public class Prob214ShortestPalindrome {
    public String shortestPalindrome(String s) {
        return s;
    }

    // Time Exceeded
    private String bruteForceSolution(String s) {
        int targetLen = s.length() - 1;

        int start, end;
        boolean isPalindrome;
        for (; targetLen >= 0; targetLen--) {
            // s[0:end] 가 회문인지 확인
            start = 0;
            end = targetLen;
            isPalindrome = true;
            while (start < end) {
                if (s.charAt(start) != s.charAt(end)) {
                    isPalindrome = false;
                    break;
                }

                start++;
                end--;
            }

            if (isPalindrome) break;
        }

        StringBuilder sb = new StringBuilder(s);

        // s 앞 쪽에 나머지 부분 추가
        for (int i = targetLen + 1; i < s.length(); i++) {
            sb.insert(0, s.charAt(i));
        }

        return sb.toString();
    }

    private String recursiveSolution(String s) {
        int len = s.length();

        // 가장 긴 시작 회문의 부분 문자열 찾기 : [0, lastMatch]
        // => 뒤에서부터 시작하여 일치하는 문자들
        int lastMatch = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(lastMatch)) {
                lastMatch++;
            }
        }

        // 회문인 경우 바로 리턴
        if (lastMatch == len) return s;

        // [lastMatch, len-1] 까지 뒤집어 준다
        String reverse = new StringBuilder(s.substring(lastMatch)).reverse().toString();

        return reverse + recursiveSolution(s.substring(0, lastMatch)) + s.substring(lastMatch);
    }

    // TODO : KMP Algorithm
}
