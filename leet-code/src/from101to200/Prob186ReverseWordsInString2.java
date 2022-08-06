package from101to200;

// https://leetcode.com/problems/reverse-words-in-a-string-ii/
public class Prob186ReverseWordsInString2 {
    public void reverseWords(char[] s) {
        int numChars = s.length;

        // reverse the array
        reverse(s, 0, numChars - 1);

        // reverse each word
        int start = 0, end;
        while (start < numChars) {
            // 공백 문자 찾기
            end = start;
            while (end < numChars && s[end] != ' ') end++;

            reverse(s, start, end - 1);

            start = end + 1;
        }
    }

    private void reverse(char[] s, int start, int end) {
        char temp;
        while (start < end) {
            temp = s[start];
            s[start++] = s[end];
            s[end--] = temp;
        }
    }
}
