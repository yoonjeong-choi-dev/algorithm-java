package from1201to1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/remove-interval/
public class Prob1272RemoveInterval {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> ans = new ArrayList<>();

        int start = toBeRemoved[0], end = toBeRemoved[1];
        for (int[] interval : intervals) {
            if (interval[1] <= start || end <= interval[0]) {
                // Case 1 : disjoint
                ans.add(Arrays.asList(interval[0], interval[1]));
            } else {
                // Case 2 : overlapped
                if (interval[0] < start) {
                    // left remaining part
                    ans.add(Arrays.asList(interval[0], start));
                }
                if (end < interval[1]) {
                    // right remaining part
                    ans.add(Arrays.asList(end, interval[1]));
                }
            }
        }

        return ans;
    }
}
