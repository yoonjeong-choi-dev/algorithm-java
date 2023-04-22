package from1701to1800;

// https://leetcode.com/problems/equal-sum-arrays-with-minimum-number-of-operations/
public class Prob1775EqualSumArraysWithMinimumNumberOfOperations {
    public int minOperations(int[] nums1, int[] nums2) {
        // 불가능한 경우 : 배열1의 최대합보다 배열2의 최소합이 큰 경우
        if (nums1.length * 6 < nums2.length || nums2.length * 6 < nums1.length) return -1;

        int sum1 = 0, sum2 = 0;
        for (int num : nums1) sum1 += num;
        for (int num : nums2) sum2 += num;

        // 이미 같은 경우
        if (sum1 == sum2) return 0;

        // 작은 쪽에서 큰 쪽으로 맞춘다
        return sum1 < sum2 ? helper(nums1, sum1, nums2, sum2) : helper(nums2, sum2, nums1, sum1);

    }

    private int helper(int[] smaller, int smallerSum, int[] larger, int largerSum) {
        // 작은 쪽은 키우고, 큰 쪽은 줄인다
        int diff = largerSum - smallerSum;

        int[] counter = new int[6];

        // 줄일 수 있는 숫자 개수
        for (int n : larger) counter[n - 1]++;

        // 증가 시킬 수 있는 숫자 개수
        for (int n : smaller) counter[6 - n]++;

        int ans = 0;
        int curChangeNum;
        for (int i = 5; i > 0; i--) {
            // 현재 차이를 줄일 수 있는 만큼 줄인다
            curChangeNum = Math.min(counter[i], diff / i + ((diff % i == 0) ? 0 : 1));
            diff -= i * curChangeNum;
            ans += curChangeNum;

            // diff <= 0 => -(i+1) < diff <= 0 : 마지막 하나를 적정값으로 변경하면 됨
            // => diff <= 0 이면 끝
            if (diff <= 0) break;
        }

        return ans;
    }

}
