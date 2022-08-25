package from501to600;

import java.util.Random;

// https://leetcode.com/problems/random-pick-with-weight/
public class Prob528RandomPickWithWeight {
    class Solution {

        private final Random random = new Random();
        private int totalSum;
        private int[] partialSum;

        public Solution(int[] w) {
            partialSum = new int[w.length];
            partialSum[0] = w[0];
            for (int i = 1; i < w.length; i++) partialSum[i] = partialSum[i - 1] + w[i];

            totalSum = partialSum[w.length - 1];
        }

        public int pickIndex() {
            // find i s.t partialSum[i-1] <= target < partialSum[i]
            int target = random.nextInt(totalSum);

            // 바운더리 처리
            if (target < partialSum[0]) return 0;

            int left = 1, right = partialSum.length - 1;
            int mid;
            while (left < right) {
                mid = left + (right - left) / 2;
                if (partialSum[mid - 1] <= target && target < partialSum[mid]) return mid;
                else if (partialSum[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return left;
        }
    }
}
