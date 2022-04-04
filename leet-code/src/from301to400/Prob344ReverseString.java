package from301to400;

// https://leetcode.com/problems/reverse-string/
public class Prob344ReverseString {

    // 인메모리 구현
    public void reverseString(char[] s) {
        int len = s.length;

        // 홀수 길이는 중간값 이전까지에 대해서만 교환
        // 짝수 길이는 절반 이하에 대해서만 교환
        int mid = len / 2;

        // 양 끝의 문자 2개를 교환하는 형태로 구현
        char temp;
        for (int i = 0; i < mid; i++) {
            temp = s[i];
            s[i] = s[len - 1 - i];
            s[len - 1 - i] = temp;
        }
    }
}
