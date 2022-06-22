package from201to300;

import java.util.Arrays;

// https://leetcode.com/problems/wiggle-sort/
public class Prob280WiggleSort {
    public void wiggleSort(int[] nums) {
        //solutionWithBuiltinSort(nums);
        linearSolutionWithoutBuiltinSort(nums);
    }

    // 시간 복잡도 : O(nlog(n)), 공간 복잡도 : O(1)
    private void solutionWithBuiltinSort(int[] nums) {
        // 오름차 순으로 정렬
        Arrays.sort(nums);

        // 조건 : nums[0] <= nums[1] >= nums[2] <= nums[3]...
        // => swap(2i+1, 2i+2), i>=0
        int upperBound = nums.length - 1;
        int temp;
        for (int i = 1; i < upperBound; i += 2) {
            temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
        }
    }

    private void linearSolutionWithoutBuiltinSort(int[] nums) {
        // 조건 : nums[0] <= nums[1]
        // => nums[-1] >= nums[0] : false 로 초기화
        boolean prevIncrease = false;
        int temp;
        for (int i = 1; i < nums.length; i++) {
            // 이전 증가 방향가 같은 경우 swap
            // nums[i] vs nums[i+1] when nums[i-1] <= nums[i] : nums[i] < nums[i+1] => swap(nums[i], nums[i+1])
            if ((prevIncrease && nums[i - 1] < nums[i]) || (!prevIncrease && nums[i - 1] > nums[i])) {
                temp = nums[i - 1];
                nums[i - 1] = nums[i];
                nums[i] = temp;
            }

            // 이전 증가 방향과 반대로 설정
            prevIncrease = !prevIncrease;
        }
    }
}
