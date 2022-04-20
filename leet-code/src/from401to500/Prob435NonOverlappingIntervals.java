package from401to500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/non-overlapping-intervals/
public class Prob435NonOverlappingIntervals {

    // 프로그래머스 문제 : 단속카메라 해결책 차용(https://programmers.co.kr/learn/courses/30/lessons/42884)
    public int eraseOverlapIntervals(int[][] intervals) {
        // 시작점이 작은 순서로 정렬
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    // 시작점이 작은 순서로 정렬
                    return o1[0] - o2[0];
                } else {
                    // 시작점이 같은 경우, 끝점이 낮은 순서로 정렬
                    return o1[1] - o2[1];
                }
            }
        });

        // 중첩되는 범위 개수 저장
        List<Integer> numOverlaps = new ArrayList<>();

        // 중첩되는 범위의 시작점 및 끝점 저장
        int start = intervals[0][0], end = intervals[0][1];
        int curStartIdx = 0;

        for (int i = 0; i < intervals.length; i++) {

            if (intervals[i][0] < end) {
                // i 번째 범위가 현재 범위와 중첩되는 경우 : i 번째 시작점이 현재 범위의 끝점보다 작은 경우
                start = Math.max(start, intervals[i][0]);
                end = Math.min(end, intervals[i][1]);
            } else {
                // i 번째 범위가 현재 범위와 중첩되지 않는 경우 : 중첩되는 범위 개수 저장 및 현재 범위 정보 업데이트
                numOverlaps.add(i - curStartIdx);
                start = intervals[i][0];
                end = intervals[i][1];
                curStartIdx = i;
            }
        }

        // 마지막 처리
        if (curStartIdx != intervals.length - 1) {
            numOverlaps.add(intervals.length - curStartIdx);
        }


        int ans = 0;
        for (int numOverlap : numOverlaps) {
            ans += numOverlap - 1;
        }
        return ans;
    }
}
