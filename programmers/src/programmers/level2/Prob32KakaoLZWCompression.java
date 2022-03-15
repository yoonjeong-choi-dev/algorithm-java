package programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// https://programmers.co.kr/learn/courses/30/lessons/17684
public class Prob32KakaoLZWCompression {
    public int[] solution(String msg) {
        int len = msg.length();

        // Step 1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
        HashMap<String, Integer> dict = new HashMap<>();
        for (char i = 0; i < 26; i++) {
            char c = (char) ('A' + i);
            dict.put(Character.toString(c), i + 1);
        }

        List<Integer> answer = new ArrayList<>(len);
        StringBuilder temp = new StringBuilder(msg);
        int start = 0;
        boolean notContained;   // Step2 탐색 시, 문자열 끝까지 가는 경우
        while(start < len) {
            // Step 2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 temp를 찾는다
            temp.setLength(0);
            notContained = false;
            for (int i = start; i < len; i++) {
                temp.append(msg.charAt(i));
                if (!dict.containsKey(temp.toString())) {
                    notContained = true;
                    break;
                }
            }

            // Step 3. temp에 해당하는 사전의 색인 번호를 출력하고, 입력에서 temp를 제거한다.
            if(notContained) temp.setLength(temp.length() -1);
            answer.add(dict.get(temp.toString()));
            start += temp.length();

            // Step 4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c), temp+c에 해당하는 단어를 사전에 등록
            if (start < len) {
                temp.append(msg.charAt(start));
                dict.put(temp.toString(), dict.size() + 1);
            }
        }


        return answer.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Prob32KakaoLZWCompression sol = new Prob32KakaoLZWCompression();

        String[] messages = {
                "KAKAO",
                "TOBEORNOTTOBEORTOBEORNOT",
                "ABABABABABABABAB"
        };

        int[][] ans = {
                {11, 1, 27, 15},
                {20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34},
                {1, 2, 27, 29, 28, 31, 30}
        };
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int[] mySol = sol.solution(messages[i]);
            if (Arrays.equals(ans[i], mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }

    }
}
