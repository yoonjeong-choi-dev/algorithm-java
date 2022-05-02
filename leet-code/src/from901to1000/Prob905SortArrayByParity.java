package from901to1000;

// https://leetcode.com/problems/sort-array-by-parity/
public class Prob905SortArrayByParity {
    public int[] sortArrayByParity(int[] nums) {
        int left = 0, right = nums.length - 1;
        int temp;
        while (left < right) {
            if (nums[left] % 2 == 1 && nums[right] % 2 == 0) {
                temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                left++;
                right--;
            } else if (nums[left] % 2 == 1) {
                right--;
            } else {
                left++;
            }
        }

        return nums;
    }
}
