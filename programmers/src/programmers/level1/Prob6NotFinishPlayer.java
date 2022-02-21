package programmers.level1;

import java.util.HashMap;

// https://programmers.co.kr/learn/courses/30/lessons/42576?language=java
public class Prob6NotFinishPlayer {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        // 완주한 리스트에서 참가자를 비교하는 방식
        HashMap<String, Integer> result = new HashMap<>();
        for(String s : completion) {
            if(result.containsKey(s))
                result.put(s, result.get(s) + 1);
            else
                result.put(s, 1);
        }

        // 완주자 리스트에 없거나 값이 0인 경우 완주하지 못한 것
        for(String s : participant) {
            if(!result.containsKey(s) || result.get(s) == 0) {
                answer = s;
                break;
            }
            result.put(s, result.get(s) - 1);
        }
        return answer;
    }

    public static void main(String[] args) {
        Prob6NotFinishPlayer sol = new Prob6NotFinishPlayer();

        String[][] parts = {
                {"leo", "kiki", "eden"},
                {"marina", "josipa", "nikola", "vinko", "filipa"},
                {"mislav", "stanko", "mislav", "ana"}
        };

        String[][] fins = {
                {"eden", "kiki"},
                {"josipa", "filipa", "marina", "nikola"},
                {"stanko", "ana", "mislav"}
        };

        String[] ans = {"leo", "vinko", "mislav"};

        int numProblems = ans.length;

        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            String mySol = sol.solution(parts[i], fins[i]);
            if (mySol.equals(ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
