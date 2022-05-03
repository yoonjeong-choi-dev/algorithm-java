package from301to400;

import java.util.Random;

// https://leetcode.com/problems/shuffle-an-array/
public class Prob384ShuffleArray {
    class Solution {

        Random random = new Random();

        int[] nums;
        int[] origin;
        int size;

        public Solution(int[] nums) {
            this.nums = nums;
            this.origin = nums.clone();
            size = nums.length;
        }

        public int[] reset() {
            nums = origin.clone();
            return nums;
        }

        public int[] shuffle() {
            int temp, changeIdx;
            for (int i = size - 1; i > 0; i--) {
                changeIdx = random.nextInt(i+1);
                temp = nums[i];
                nums[i] = nums[changeIdx];
                nums[changeIdx] = temp;
            }

            return nums;
        }

    }
}
