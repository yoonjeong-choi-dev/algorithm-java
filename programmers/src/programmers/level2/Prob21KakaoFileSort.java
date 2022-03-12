package programmers.level2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// https://programmers.co.kr/learn/courses/30/lessons/17686
public class Prob21KakaoFileSort {
    public String[] solution(String[] files) {
        Comparator<String[]> comparator = new Comparator<String[]>() {
            @Override
            public int compare(String[] s1, String[] s2) {
                String head1 = s1[0].toLowerCase();
                String head2 = s2[0].toLowerCase();

                if (head1.equals(head2)) {
                    // 헤드가 같은 경우에는 그 다음 나오는 숫자 비교
                    int number1 = Integer.parseInt(s1[1]);
                    int number2 = Integer.parseInt(s2[1]);

                    return number1 - number2;
                } else {
                    // 헤드가 다른 경우는 사전 순
                    return head1.compareTo(head2);
                }
            }
        };

        int fileNum = files.length;

        // 각 파일명을 (헤드, 숫자, 테일) 형태로 파싱
        String[][] parsed = new String[fileNum][];
        for (int i = 0; i < fileNum; i++) {
            parsed[i] = parsing(files[i]);
        }

        Arrays.sort(parsed, comparator);


        String[] answer = new String[fileNum];
        for (int i = 0; i < fileNum; i++) {
            answer[i] = parsed[i][0] + parsed[i][1] + parsed[i][2];
        }
        return answer;
    }

    private String[] parsing(String file) {
        String[] ret = new String[3];

        Matcher matcher = Pattern.compile("[0-9]+").matcher(file);
        matcher.find();

        int digitStart = matcher.start();
        ret[0] = file.substring(0, digitStart);

        int lastIdx = digitStart + 1;
        for (; lastIdx < file.length(); lastIdx++) {
            if (!Character.isDigit(file.charAt(lastIdx))) break;
        }

        ret[1] = file.substring(digitStart, lastIdx);
        ret[2] = file.substring(lastIdx);

        return ret;
    }

    public static void main(String[] args) {
        Prob21KakaoFileSort sol = new Prob21KakaoFileSort();

        String[][] files = {
                {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"},
                {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"}
        };

        String[][] ans = {
                {"img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"},
                {"A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"}
        };

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            String[] mySol = sol.solution(files[i]);
            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
