package programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/81302
public class Prob37KakaoSocialDistancing {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            answer[i] = checkValid(places[i]) ? 1 : 0;
        }

        return answer;
    }

    private boolean checkValid(String[] place) {
        // 사람들 위치 저장
        ArrayList<int[]> people = new ArrayList<>();
        for (int i = 0; i < place.length; i++) {
            for (int j = 0; j < place[i].length(); j++) {
                if (place[i].charAt(j) == 'P') {
                    people.add(new int[]{i, j});
                }
            }
        }

        // 각 사람들에 대해서 거리두기 확인
        int numPeople = people.size();
        int rowDist, colDist;
        for (int i = 0; i < numPeople - 1; i++) {
            for (int j = i + 1; j < numPeople; j++) {
                int[] p1 = people.get(i);
                int[] p2 = people.get(j);

                // 항상 p1 이 p2보다 왼쪽에 존재
                if (p1[1] > p2[1]) {
                    int[] temp = p1;
                    p1 = p2;
                    p2 = temp;
                }

                rowDist = p1[0] - p2[0];
                colDist = p1[1] - p2[1];

                // 맨하튼 거리가 3이상이면 OK
                if (Math.abs(rowDist) + Math.abs(colDist) > 2) continue;

                if (rowDist == 0) {
                    // 같은 행에 있는 경우 : 수직선 상에 하나의 공간이 존재
                    if (place[p1[0]].charAt(p1[1] + 1) != 'X') {
                        return false;
                    }

                } else if (colDist == 0) {
                    // 같은 열에 있는 경우 : 수평선 상에 하나의 공간이 존재
                    if (rowDist < 0) {
                        if (place[p1[0] + 1].charAt(p1[1]) != 'X') {
                            return false;
                        }
                    } else {
                        if (place[p2[0] + 1].charAt(p2[1]) != 'X') {
                            return false;
                        }
                    }
                } else {
                    // 대각선 상에 존재
                    if (place[p1[0]].charAt(p2[1]) != 'X') return false;
                    if (place[p2[0]].charAt(p1[1]) != 'X') return false;
                }
            }
        }


        return true;
    }

    public static void main(String[] args) {
        Prob37KakaoSocialDistancing sol = new Prob37KakaoSocialDistancing();

        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        int[] ans = {1,0,1,1,1};

        int[] mySol = sol.solution(places);
        if(Arrays.equals(ans, mySol)) {
            System.out.println("PASS");
        } else {
            System.out.println("Result : " + Arrays.toString(mySol));
            System.out.println("Expected : " + Arrays.toString(ans));
        }
    }
}
