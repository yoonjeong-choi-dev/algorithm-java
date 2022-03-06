package programmers.level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Prob8KakaoTuple {
    public int[] solution(String s) {
        // 가장 바깥쪽 중괄호 제거
        s = s.substring(1, s.length()-1);

        // { .. } 형태로 구분
        String[] pairs = s.split("\\{");

        HashMap<Integer, HashSet<Integer>> tuples = new HashMap<>();

        int maxLen = 0;
        for(int i=1;i<pairs.length;i++){
            // 중괄호 제거
            String pair = pairs[i].replaceAll("[{}]", "");
            String[] numStrs = pair.split(",");

            int len = numStrs.length;
            if(maxLen < len) maxLen = len;
            tuples.put(len, new HashSet<>());
            for (String numStr : numStrs) {
                tuples.get(len).add(Integer.parseInt(numStr));
            }
        }

        int[] answer = new int[maxLen];

        answer[0] = tuples.get(1).iterator().next();
        for(int i=1;i<maxLen;i++){
            HashSet<Integer> prevSet = tuples.get(i);
            for(int n : tuples.get(i+1)) {
                if(!prevSet.contains(n)) {
                    answer[i] = n;
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Prob8KakaoTuple sol = new Prob8KakaoTuple();

        String[] tuples = {
                "{{2},{2,1},{2,1,3},{2,1,3,4}}",
                "{{1,2,3},{2,1},{1,2,4,3},{2}}",
                "{{20,111},{111}}",
                "{{123}}",
                "{{4,2,3},{3},{2,3,4,1},{2,3}}"
        };
        int[][] ans = {
                {2,1,3,4},
                {2,1,3,4},
                {111,20},
                {123},
                {3,2,4,1}
        };

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            int[] mySol = sol.solution(tuples[i]);
            if (Arrays.equals(ans[i], mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
