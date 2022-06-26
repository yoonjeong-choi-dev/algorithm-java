package from201to300;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/meeting-rooms/
public class Prob252MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        int len = intervals.length;
        if (len < 2) return true;

        // 먼저 시작하는 시간 순서
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return o1[0] - o2[0];
                else return o1[1] - o2[1];
            }
        });

        int[] prev = intervals[0];
        int[] cur;
        for (int i = 1; i < len; i++) {
            cur = intervals[i];

            // 겹치는 경우
            if (prev[1] > cur[0]) return false;

            prev = cur;
        }

        return true;
    }
}
