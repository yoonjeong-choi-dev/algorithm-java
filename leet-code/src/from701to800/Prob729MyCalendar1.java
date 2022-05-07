package from701to800;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/my-calendar-i/
public class Prob729MyCalendar1 {
    class MyCalendar {
        private List<int[]> booked = new ArrayList<>(1000);

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            boolean ans = true;
            for (int[] b : booked) {
                // 유효한 범위 : end <= b[0] || b[1] <= start
                if(end > b[0] && b[1] >start) {
                    ans = false;
                    break;
                }
            }

            if (ans) {
                booked.add(new int[]{start, end});
            }

            return ans;
        }
    }
}
