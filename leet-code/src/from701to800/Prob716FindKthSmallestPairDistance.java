package from701to800;

import java.util.Arrays;

// https://leetcode.com/problems/find-k-th-smallest-pair-distance/
public class Prob716FindKthSmallestPairDistance {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;

        // Binary Search to the answer
        int low = 0, high = nums[len - 1];
        int ans = high;
        int mid, count, left, right;
        while (low <= high) {
            mid = (low + high) / 2;
            left = 0;

            // mid 이하의 차이를 가지는 pair 개수
            count = 0;
            for (right = 0; right < len; right++) {
                // 오름차순으로 정렬되어 있으므로, 현재 nums[right]을 기준으로 mid 값보다 작은 left가 나올 때까지 이동
                while (nums[right] - nums[left] > mid) left++;

                // (left~right-1) 과 nums[right] 차이는 mid 이하
                count += right - left;
            }

            if (count >= k) {
                // 가장 작은 거리를 찾아야 하므로, 같을 떄도 계속 검색 필요
                // 현재 mid 값이 너무 커서, pair 개수가 많음
                ans = mid;
                high = mid - 1;
            } else {
                // count < k => 현재 mid 값이 너무 작아서 pair 개수가 적음
                low = mid + 1;
            }
        }
        return ans;
    }
}
