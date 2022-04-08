package from1to100;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/permutations/
public class Prob46Permutations {
    private List<List<Integer>> ans;
    private int[] nums;
    private int len;

    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        len = nums.length;

        int totalSize = 1;
        for (int i = 1; i <= len; i++) {
            totalSize *= i;
        }
        ans = new ArrayList<>(totalSize);

        int[] order = new int[len];
        for (int i = 0; i < len; i++) {
            order[i] = -1;
        }

        recursiveSearch(order, 0);

        return ans;
    }

    private void recursiveSearch(int[] order, int numVisited) {
        if (numVisited == len) {
            ArrayList<Integer> ret = new ArrayList<>(len);
            for (int i = 0; i < len; i++) {
                ret.add(nums[order[i]]);
            }
            ans.add(ret);
            return;
        }

        for (int i = 0; i < len; i++) {
            // i 인덱스 방문 후 재귀 호출
            if (order[i] == -1) {
                order[i] = numVisited;
                recursiveSearch(order, numVisited + 1);
                order[i] = -1;
            }
        }
    }

}
