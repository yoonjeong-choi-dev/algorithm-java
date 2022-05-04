package from1001to1100;

// https://leetcode.com/problems/missing-element-in-sorted-array/
public class Prob1060MissingElementInSortedArray {
    public int missingElement(int[] nums, int k) {
        // return linearSolution(nums, k);
        return binarySearchSolution(nums, k);
    }

    private int linearSolution(int[] nums, int k) {
        int missing;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] + 1 == nums[i]) continue;

            missing = nums[i] - nums[i - 1] - 1;
            if (missing >= k) {
                return nums[i - 1] + k;
            }

            k -= missing;
        }

        return nums[nums.length - 1] + k;
    }

    private int binarySearchSolution(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int mid, missingNum;
        while (right - left > 1) {
            mid = (left + right) / 2;

            // [left, mid] 까지의 missing number 개수 구하기
            missingNum = nums[mid] - nums[left] - (mid - left);

            if (missingNum >= k) {
                // [left, mid] 범위에 정답 존재
                right = mid;
            } else {
                // missingNum < k: 오른쪽 탐색 필요
                left = mid;
                k -= missingNum;
            }
        }

        missingNum = nums[right] - nums[left] - (right - left);

        return missingNum >= k ? nums[left]+k : nums[right] +k -missingNum;
    }
}
