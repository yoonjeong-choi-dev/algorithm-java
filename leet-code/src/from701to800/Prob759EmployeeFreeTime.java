package from701to800;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/employee-free-time/
public class Prob759EmployeeFreeTime {
    class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> allWorkingTimes = new ArrayList<>();
        for (List<Interval> time : schedule) allWorkingTimes.addAll(time);

        allWorkingTimes.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start != o2.start) return o1.start - o2.start;
                else return o1.end - o2.end;
            }
        });

        List<Interval> ans = new ArrayList<>();
        int curStart = allWorkingTimes.get(0).start, curEnd = allWorkingTimes.get(0).end;
        for (Interval interval : allWorkingTimes) {
            if (curEnd < interval.start) {
                ans.add(new Interval(curEnd, interval.start));
                curStart = interval.start;
                curEnd = interval.end;
            } else {
                curStart = Math.min(curStart, interval.start);
                curEnd = Math.max(curEnd, interval.end);
            }
        }
        return ans;
    }
}
