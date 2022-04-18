package from101to200;

// https://leetcode.com/problems/find-peak-element/
public class Prob162FindPeakElement {
    public int findPeakElement(int[] nums) {
        int maxIdx = nums.length - 1;
        int left = 0, right = maxIdx;
        int mid;

        // nums[-1] = nums[n] = -inf 조건을 이용한 이분 탐색
        while (left <= right) {
            mid = (left + right) / 2;
            if (mid == 0 || mid == maxIdx) {
                // (left,right) = (0,1) or (0,0) || (len-2, len-1)
                return nums[left] > nums[right] ? left : right;
            }

            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid - 1] < nums[mid]) {
                // nums[mid-1] < nums[mid] <= nums[mid+1]
                // nums[n] = -inf => mid 이후 인덱스에 반드시 정답 존재
                left = mid + 1;
            } else {
                // nums[mid-1] >= nums[mid] > nums[mid+1]
                // nums[-1]  = -inf => mid 이전 인덱스에 반드시 정답 존재
                right = mid - 1;
            }
        }

        // left == right
        return left;
    }
}
