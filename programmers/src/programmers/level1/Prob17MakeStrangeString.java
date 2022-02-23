package programmers.level1;

// https://programmers.co.kr/learn/courses/30/lessons/12930
public class Prob17MakeStrangeString {
    public String solution(String s) {
        int len = s.length();
        StringBuilder answer = new StringBuilder(len);

        int curIdx = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ' ') {
                curIdx = 0;
                answer.append(' ');
                continue;
            }

            if (curIdx % 2 == 0) {
                answer.append(Character.toUpperCase(s.charAt(i)));
            } else {
                answer.append(Character.toLowerCase(s.charAt(i)));
            }
            curIdx++;
        }

        return answer.toString();
    }
}
