package programmers.level2;

import java.util.ArrayList;
import java.util.List;

// https://programmers.co.kr/learn/courses/30/lessons/42860
public class Prob28Joystick {
    public int solution(String name) {

        // 각 문자에 대해서 수직방향으로 움직여야 하는 개수
        int totalVertical = 0;

        // a의 위치 : [0] - 시작점 [1] - 끝점
        List<int[]> aPos = new ArrayList<>(name.length());

        boolean isFind = false;
        for (int i = 0; i < name.length(); i++) {
            int diff = name.charAt(i) - 'A';
            totalVertical += diff <= 13 ? diff : 26 - diff;


            // A 인 경우
            if (name.charAt(i) == 'A') {
                if (!isFind) {
                    // 시작점 인 경우
                    isFind = true;
                    aPos.add(new int[]{i, i});
                } else {
                    // 시작점이 아닌 경우
                    aPos.get(aPos.size() - 1)[1] = i;
                }
            } else {
                isFind = false;
            }
        }


        // 수평방향으로 움직여야 하는 횟수
        int totalHorizontal = name.length() - 1;

        // 방법 1 : 오른쪽 -> A 직전 -> 왼쪽 -> 0 -> -1 -> 왼쪽
        // 방법 2 : 왼쪽 -> A 직전 -> 오른쪽 -> -1 -> 0 ->
        for (int[] pos : aPos) {
            // 특이 케이스 1) A로 시작하는 경우 : 오른쪽 -> 마지막 A
            if (pos[0] == 0) {
                totalHorizontal = Math.min(totalHorizontal, name.length() - (pos[1] + 1));
                continue;
            }

            // 특이 케이스 2) A로 끝나는 경우 : 왼쪽 -> 마지막 A
            if (pos[1] == name.length()) {
                totalHorizontal = Math.min(totalHorizontal, pos[0] - 1);
                continue;
            }

            // 방법 1
            // A 만나기 직전까지 오른쪽 이동
            int total = pos[0] - 1;

            // 왼쪽으로 이동하여 맨 왼쪽 A 직전까지
            total += pos[0];

            // 맨 오른쪽에서 A 만나기 직전까지 왼쪽으로 이동
            total += (name.length() - 1) - (pos[1] + 1);
            totalHorizontal = Math.min(totalHorizontal, total);

            // 방법 2
            // 오른쪽으로 갔다가 다시 0으로 돌아옴
            total = 2 * (name.length() - (pos[1] + 1));

            // 왼쪽으로 이동하여 맨 왼쪽 A 직전까지
            total += pos[0] - 1;
            totalHorizontal = Math.min(totalHorizontal, total);
        }

        //System.out.printf("수직 : %d, 수평 : %d\n", totalVertical, totalHorizontal);

        return totalVertical + totalHorizontal;
    }

    public static void main(String[] args) {
        Prob28Joystick sol = new Prob28Joystick();
        String[] names = {"JEROEN", "JAN", "BBABAAAABBBAAAABABB", "BBAAAAAABBBAAAAAABB", "BBBAAAAAAAB", "BBBAAAAABAAAAAAAAAAA", "BAAAAAAAAAABAAAAAABB", "AAABBAB"};

        int[] ans = {56, 23, 26, 20, 8, 12, 13, 7};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================= Problem %d\n", i);
            int mySol = sol.solution(names[i]);
            if (ans[i] == mySol) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }

    }
}
