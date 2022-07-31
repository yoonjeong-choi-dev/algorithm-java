package from1101to1200;

// https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/
public class Prob1150CheckIfANumberIsMajorityElementInASortedArray {

    public boolean isMajorityElement(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;

        int mid;
        // target 값 중 가장 왼쪽 인덱스 찾기
        while (left < right) {
            if (nums[left] == target) break;

            mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // target 보다 작은 경우에는 오른쪽 탐색
                left = mid + 1;
            } else {
                // target 이상인 경우 왼쪽 탐색
                // 같은 경우에도 가장 왼쪽 인덱스를 찾기 위해서 오른쪽 범위를 mid 로 줄임
                right = mid;
            }
        }

        // target 이 배열에 없는 경우
        if (nums[left] != target) return false;

        // majority 개수 : len/2 +1 이상
        int minRightIdx = left + len / 2;

        return minRightIdx < len && nums[minRightIdx] == target;
    }
}
