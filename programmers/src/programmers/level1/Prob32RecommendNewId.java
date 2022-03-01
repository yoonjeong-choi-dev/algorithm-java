package programmers.level1;

import java.util.regex.Pattern;

// https://programmers.co.kr/learn/courses/30/lessons/72410
public class Prob32RecommendNewId {

    public String solution(String new_id) {
        String answer = new_id.toLowerCase();

        // 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자
        Pattern pattern = Pattern.compile("[^a-z0-9.\\-_]");
        answer = pattern.matcher(answer).replaceAll("");

        // . 이 두번이상 -> .
        pattern = Pattern.compile("\\.+");
        answer = pattern.matcher(answer).replaceAll(".");

        // 처음과 끝 .은 제거
        if (!answer.isEmpty() && answer.charAt(0) == '.') {
            answer = answer.substring(1);
        }
        if (!answer.isEmpty() && answer.charAt(answer.length() - 1) == '.') {
            answer = answer.substring(0, answer.length() - 1);
        }

        // new_id가 빈 문자열이라면, new_id에 "a"를 대입
        if (answer.isEmpty()) answer = "a";

        // new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거 및 마지막 점 제거
        if (answer.length() >= 16) answer = answer.substring(0, 15);
        if (answer.charAt(answer.length() - 1) == '.') {
            answer = answer.substring(0, answer.length() - 1);
        }

        // new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복
        while (answer.length() < 3) {
            answer = answer + answer.charAt(answer.length() - 1);
        }

        return answer;
    }

    public static void main(String[] args) {
        Prob32RecommendNewId sol = new Prob32RecommendNewId();

        String[] ids = {"...!@BaT#*..y.abcdefghijklm", "z-+.^.", "=.=", "123_.def", "abcdefghijklmn.p"};
        String[] ans = {"bat.y.abcdefghi", "z--", "aaa", "123_.def", "abcdefghijklmn"};

        int numProblems = ans.length;
        for (int i=0;i<numProblems;i++) {
            System.out.printf("Problem %d\n", i);
            String mySol = sol.solution(ids[i]);
            if (ans[i].equals(mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
