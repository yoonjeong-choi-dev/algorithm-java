package programmers.level3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://programmers.co.kr/learn/courses/30/lessons/64064
public class Prob13InvalidUser {
    private int totalBanned;
    private List<List<String>> candidates;
    private Set<Set<String>> resultBanned;

    public int solution(String[] user_id, String[] banned_id) {
        totalBanned = banned_id.length;

        // 각 불량 사용자 아이디에 대해서 후보 사용자 추출
        candidates = new ArrayList<>(totalBanned);
        for (int i = 0; i < totalBanned; i++) {
            List<String> candidate = new ArrayList<>();
            for (String user : user_id) {
                if (isCandidate(user, banned_id[i])) candidate.add(user);
            }
            candidates.add(candidate);
        }

        resultBanned = new HashSet<>();

        // 후보 제재 아이디에 대해서 dfs : 8^8 == 16777216 => 시간 복잡도 pass
        List<String> users = new ArrayList<>(totalBanned);
        dfs(0, users);
        return resultBanned.size();
    }

    private boolean isCandidate(String user, String banned) {
        // 길이가 다르면 무조건 거짓
        if (user.length() != banned.length()) return false;

        for (int i = 0; i < user.length(); i++) {
            // 밴 아이디의 와일드카드는 무조건 패스
            if (banned.charAt(i) == '*') continue;

            if (user.charAt(i) != banned.charAt(i)) return false;
        }

        return true;
    }

    private void dfs(int index, List<String> users) {

        // 모든 탐색 완료 시
        if (index == totalBanned) {
            // 제대로 탐색된 경우에만 결과 집합에 추가 : 중복된 사용자가 없는 경우
            HashSet<String> result = new HashSet<>(users);
            if (result.size() == totalBanned) {
                resultBanned.add(result);
            }
            return;
        }

        // 현재 불량 사용자 후보 군에서 하나를 뽑고 dsf
        List<String> curBanned = candidates.get(index);
        for (String ban : curBanned) {
            users.add(ban);
            dfs(index + 1, users);
            users.remove(users.size() -1);
        }
    }

    public static void main(String[] args) {
        Prob13InvalidUser sol = new Prob13InvalidUser();

        String[][] users = {
                {"frodo", "fradi", "crodo", "abc123", "frodoc"},
                {"frodo", "fradi", "crodo", "abc123", "frodoc"},
                {"frodo", "fradi", "crodo", "abc123", "frodoc"}
        };
        String[][] banned = {
                {"fr*d*", "abc1**"},
                {"*rodo", "*rodo", "******"},
                {"fr*d*", "*rodo", "******", "******"}
        };

        int[] ans = {2, 2, 3};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.solution(users[i], banned[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
