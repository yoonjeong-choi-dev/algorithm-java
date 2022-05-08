package from1201to1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/meeting-scheduler/
public class Prob1229MeetingScheduler {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {

        // 시작 시간, 끝나는 시간에 대한 오름차순
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        };
        Arrays.sort(slots1, comparator);
        Arrays.sort(slots2, comparator);

        int len1 = slots1.length, len2 = slots2.length;
        int i1 = 0, i2 = 0;
        int start, end;
        while (i1 < len1 && i2 < len2) {
            if (slots1[i1][1] <= slots2[i2][0]) {
                // 슬롯 1의 현재 시간이 너무 이름
                i1++;
            } else if (slots2[i2][1] <= slots1[i1][0]) {
                // 슬롯 2의 현재 시간이 너무 이름
                i2++;
            } else {
                start = Math.max(slots1[i1][0], slots2[i2][0]);
                end = Math.min(slots1[i1][1], slots2[i2][1]);
                if (end - start >= duration) {
                    return new ArrayList<>(Arrays.asList(start, start + duration));
                }

                // 둘 중 빨리 끝나는 시간을 이동
                if(slots1[i1][1] < slots2[i2][1]) {
                    i1++;
                } else {
                    i2++;
                }
            }
       }


        return new ArrayList<>();
    }
}
