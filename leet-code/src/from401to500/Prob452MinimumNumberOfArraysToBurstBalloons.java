package from401to500;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
public class Prob452MinimumNumberOfArraysToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        // 시작점 순으로 정렬
        // 시작점이 같은 경우 끝점 순으로 정렬
        // points 최대/최소값이 Int 형 최대/최소 일 경우도 있으므로, 정렬 함수에서 값에 대한 덧셈/뺄셈 사용 X
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] < o2[0] ? -1 : 1;
                } else {
                    if (o1[1] == o2[1]) return 0;
                    else return o1[1] < o2[1] ? -1 : 1;
                }
            }
        });

        int ans = 1;
        int curStart = points[0][0], curEnd = points[0][1];

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= curEnd) {
                // 현재 화살로 커버가 가능한 경우
                curStart = Math.max(curStart, points[i][0]);
                curEnd = Math.min(curEnd, points[i][1]);
            } else {
                // 현재 화살로 커버 불가능 => 화살 추가
                ans++;
                curStart = points[i][0];
                curEnd = points[i][1];
            }
        }


        return ans;
    }
}
