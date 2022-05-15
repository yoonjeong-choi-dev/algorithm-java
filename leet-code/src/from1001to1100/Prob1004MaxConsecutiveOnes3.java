package from1001to1100;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/max-consecutive-ones-iii/
// Ref : Problem 340
public class Prob1004MaxConsecutiveOnes3 {
    public int longestOnes(int[] nums, int k) {
        int size = nums.length;
        if (size <= k) return size;

        int bound = k + 1;

        // 0 들의 인덱스 저장 => bound 이상인 경우 앞의 인덱스부터 삭제해야 하므로 큐 사용
        Queue<Integer> zeroIndices = new LinkedList<>();
        int ans = k;

        int left = 0, right = 0;
        while (right < size) {
            if (nums[right] == 0) {
                zeroIndices.offer(right);
            }

            if (zeroIndices.size() == bound) {
                left = zeroIndices.poll() + 1;
            }

            right++;
            ans = Math.max(ans, right - left);
        }

        return ans;
    }
}
