package programmers.level1;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/12948
public class Prob11HidePhoneNumber {
    public String solution(String phone_number) {
        int strLen = phone_number.length();

        char[] answer = new char[strLen];
        Arrays.fill(answer, '*');
        for(int i=0;i<4;i++)
            answer[strLen-1-i] = phone_number.charAt(strLen - 1 - i);

        return new String(answer);
    }
}
