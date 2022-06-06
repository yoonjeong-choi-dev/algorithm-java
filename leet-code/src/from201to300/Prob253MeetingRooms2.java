package from201to300;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/meeting-rooms-ii/
public class Prob253MeetingRooms2 {
    public int minMeetingRooms(int[][] intervals) {
        int numIntervals = intervals.length;
        if (numIntervals == 0) return 0;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        // 현재 배정된 미팅 룸들의 끝나는 시간을 오름차순으로 저장
        PriorityQueue<Integer> endPq = new PriorityQueue<>(numIntervals, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        int ans = 1;

        // 0번 미팅룸 배정 : 가장 먼저 시작하는 미팅
        endPq.offer(intervals[0][1]);

        for (int i = 1; i < numIntervals; i++) {
            // 현재 미팅 시간이 할당된 미팅룸이 끝나고 배정되는 경우 큐에서 제거
            if (intervals[i][0] >= endPq.peek()) {
                endPq.poll();
            }

            // 현재 미팅룸 배정
            endPq.offer(intervals[i][1]);

            ans = Math.max(ans, endPq.size());
        }

        return ans;
    }
}
