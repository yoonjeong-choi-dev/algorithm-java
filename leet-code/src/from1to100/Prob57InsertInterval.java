package from1to100;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/insert-interval/
public class Prob57InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int numInterval = intervals.length;
        if (numInterval == 0) return new int[][]{{newInterval[0], newInterval[1]}};

        int addStart = newInterval[0], addEnd = newInterval[1];

        List<int[]> ansList = new ArrayList<>(numInterval);

        int idx = 0;

        // 새로 넣는 구간보다 먼저 시작하는 구간들은 그냥 저장하면 됨
        while (idx < numInterval && intervals[idx][0] < addStart) ansList.add(intervals[idx++]);

        // 추가할 구간
        int[] curInterval;
        if (ansList.isEmpty() || ansList.get(idx - 1)[1] < addStart) {
            // 비어있거나(맨 앞 추가) 마지막에 추가한 구간이랑 겹치는 부분이 없는 경우 그냥 추가
            ansList.add(newInterval);
        } else {
            // 겹치는 구간이 있는 경우 마지막에 추가한 구간에 합침
            curInterval = ansList.get(idx - 1);
            curInterval[1] = Math.max(curInterval[1], addEnd);
            // ansList.set(idx - 1, curInterval);
        }

        // 이후 구간들 추가
        while(idx < numInterval) {
            curInterval = intervals[idx++];

            if(ansList.get(ansList.size() -1)[1] < curInterval[0]) {
                // 마지막에 추가한 구간과 겹치는 부분이 없는 경우
                ansList.add(curInterval);
            } else {
                // 마지막에 추가한 구간과 겹치는 부분이 있는 경우
                ansList.get(ansList.size()-1)[1] = Math.max(ansList.get(ansList.size()-1)[1], curInterval[1]);
            }
        }

        return ansList.toArray(new int[ansList.size()][2]);
    }
}
