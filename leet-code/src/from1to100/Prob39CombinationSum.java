package from1to100;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/combination-sum/
public class Prob39CombinationSum {
    private int len;
    List<List<Integer>> ans;

    int[] nums;
    int[] counts;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        nums = candidates;
        len = nums.length;
        counts = new int[len];

        ans = new LinkedList<>();
        recursive(0, target);
        return ans;
    }

    private void recursive(int curIdx, int remain) {
        if (remain == 0) {
            List<Integer> curAns = new LinkedList<>();

            // [0:curIdx] 까지만 탐색된 결과
            for (int i = 0; i < curIdx; i++) {
                for (int j = 0; j < counts[i]; j++) curAns.add(nums[i]);
            }
            ans.add(curAns);
            return;
        } else if (curIdx == len) return;

        int maxCount = remain / nums[curIdx];
        for (int i = 0; i <= maxCount; i++) {
            counts[curIdx] = i;
            recursive(curIdx + 1, remain);
            remain -= nums[curIdx];
        }
    }
}
