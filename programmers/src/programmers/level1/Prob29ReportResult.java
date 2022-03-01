package programmers.level1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

// https://programmers.co.kr/learn/courses/30/lessons/92334
public class Prob29ReportResult {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 각 유저가 신고한 유저들 리스트
        HashMap<String, HashSet<String>> reportUsers = new HashMap<>();

        // 각 유저가 신고당한 횟수
        HashMap<String, Integer> numReports = new HashMap<>();

        // 해시맵 업데이트
        for(String s: id_list) {
            numReports.put(s, 0);
            reportUsers.put(s, new HashSet<>());
        }

        // 각 유저의 신고 정보 업데이트
        for(String r : report) {
            String[] parsed = r.split(" ");
            reportUsers.get(parsed[0]).add(parsed[1]);
        }

        // 각 유저의 신고 횟수
        for(HashSet<String> set : reportUsers.values()) {
            for(String s : set) {
                numReports.put(s, numReports.get(s) + 1);
            }
        }

        int[] answer = new int[id_list.length];
        for(int i=0;i<id_list.length;i++) {
            HashSet<String> reportSet = reportUsers.get(id_list[i]);
            for(String s : reportSet) {
                if(numReports.get(s) >= k) answer[i]++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Prob29ReportResult sol = new Prob29ReportResult();

        String[][] ids = {{"muzi", "frodo", "apeach", "neo"}, {"con", "ryan"}};
        String[][] reports = {
                {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"},
                {"ryan con", "ryan con", "ryan con", "ryan con"}
        };
        int[] thresholds = {2,3};

        int[][] ans = {{2,1,1,0}, {0,0}};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int[] mySol = sol.solution(ids[i], reports[i], thresholds[i]);

            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
