package from101to200;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/missing-ranges/
public class Prob163MissingRanges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        int len = nums.length;

        if (len == 0) {
            ans.add(getRange(lower, upper));
            return ans;
        }

        if (lower < nums[0]) {
            ans.add(getRange(lower, nums[0] - 1));
        }

        // lower <= nums[i] <= upper
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] + 1 != nums[i]) ans.add(getRange(nums[i - 1] + 1, nums[i] - 1));
        }

        if (nums[len - 1] < upper) {
            ans.add(getRange(nums[len - 1] + 1, upper));
        }
        return ans;
    }

    private String getRange(int start, int end) {
        if (start == end) return String.valueOf(start);
        else return String.format("%d->%d", start, end);
    }
}
