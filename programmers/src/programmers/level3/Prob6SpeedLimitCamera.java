package programmers.level3;

import java.util.Arrays;
import java.util.Comparator;

public class Prob6SpeedLimitCamera {
    public int solution(int[][] routes) {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    // 진입점이 빠른 순서로 정렬
                    return o1[0] - o2[0];
                } else {
                    // 진입점이 같은 경우, 나간 지점이 빠른 순서로 정렬 => 더 먼저 커버해야 하는 차량
                    return o1[1] - o2[1];
                }
            }
        };
        Arrays.sort(routes, comparator);

        // 현재 카메라로 커버해야 하는 범위
        int answer = 1;
        int min = routes[0][0], max = routes[0][1];
        for (int i = 1; i < routes.length; i++) {

            if (routes[i][0] <= max) {
                // i번째 차량이 현재 단속 카메라 범위에 있는 경우 카메라 범위 업데이트
                min = Math.max(min, routes[i][0]);
                max = Math.min(max, routes[i][1]);
            } else {
                // 현재 단속 카메라 범위에 없는 경우 카메라 설치 및 범위 설정
                answer++;
                min = routes[i][0];
                max = routes[i][1];
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        Prob6SpeedLimitCamera sol = new Prob6SpeedLimitCamera();

        int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        int ans = 2;
        int mySol = sol.solution(routes);

        if(ans == mySol) {
            System.out.println("PASS");
        } else {
            System.out.println("Result : " + mySol);
            System.out.println("Expected : " + ans);
        }
    }
}
