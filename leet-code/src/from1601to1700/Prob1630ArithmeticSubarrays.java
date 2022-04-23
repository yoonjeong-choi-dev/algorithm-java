package from1601to1700;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.com/problems/arithmetic-subarrays/
// [1,2,10,-6,-7,8,16,0,0,10,20,15,-2,-3,-1,-4,-4,-8,-2] 15 ~ 18
public class Prob1630ArithmeticSubarrays {
    int[] nums;

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        this.nums = nums;

        List<Boolean> ans = new ArrayList<>(l.length);
        for (int i = 0; i < l.length; i++) {
            ans.add(checkArithmeticWithRearrange(l[i], r[i]));
        }

        return ans;
    }

    private boolean checkArithmeticWithRearrange(int l, int r) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = l; i <= r; i++) {
            if (min > nums[i]) min = nums[i];
            if (max < nums[i]) max = nums[i];
        }

        if (min == max) return true;

        int diff = max - min;
        if (diff % (r - l) != 0) return false;
        diff /= (r - l);

        boolean[] isChecked = new boolean[r - l + 1];
        int curIdx;
        for (int i = l; i <= r; i++) {
            if ((nums[i] - min) % diff != 0) return false;
            curIdx = (nums[i] - min) / diff;
            if (isChecked[curIdx]) return false;
            isChecked[curIdx] = true;
        }

        return true;
    }

    private List<Boolean> solutionWithHeap(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>(l.length);
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        boolean isArithmetic;
        int prev, diff;
        for (int i = 0; i < l.length; i++) {
            heap.clear();
            for (int j = l[i]; j <= r[i]; j++) {
                heap.add(nums[j]);
            }

            isArithmetic = true;
            prev = heap.poll();
            diff = prev - heap.peek();
            while (!heap.isEmpty()) {
                if (diff != prev - heap.peek()) {
                    isArithmetic = false;
                    break;
                } else {
                    prev = heap.poll();
                }
            }
            ans.add(isArithmetic);
        }

        return ans;
    }
}
