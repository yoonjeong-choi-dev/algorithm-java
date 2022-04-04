package from501to600;

// https://leetcode.com/problems/reverse-words-in-a-string-iii/
public class Prob557ReverseWords {
    public String reverseWords(String s) {
        StringBuilder ans = new StringBuilder(s.length());

        // 문자열을 공백 문자를 기준으로 토큰화(단어)
        String[] tokens = s.split(" ");

        int i;
        for (String token : tokens) {
            for (i = token.length() - 1; i >= 0; i--) {
                ans.append(token.charAt(i));
            }

            // 각 단어 뒤에는 공백 문자를 추가
            ans.append(" ");
        }

        // 마지막 공백 제거
        ans.deleteCharAt(ans.length() - 1);

        return ans.toString();
    }

}
