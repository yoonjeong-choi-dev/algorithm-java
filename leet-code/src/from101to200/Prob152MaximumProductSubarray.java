package from101to200;

// https://leetcode.com/problems/maximum-product-subarray/
public class Prob152MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];

        int ans = nums[0];

        // 현재 범위에서의 최대값 및 최소값
        // 최소값의 경우, 절대값이 최대값이 되기 때문에 추적 필요
        int curMax = nums[0];
        int curMin = nums[0];

        int tempMax, tempMin, curNum;
        for (int i = 1; i < len; i++) {
            curNum = nums[i];

            // 현재 값을 곱해서 현재 최대/최소 구하기
            // : 현재 숫자가 음수일 수도 있으므로, 이전에 구한 최대/최소에 곱해서 비교
            tempMax = curMax * curNum;
            tempMin = curMin * curNum;

            // 현재 숫자와 비교
            // : 현재 수 > curMax => subarray 시작 지점이 현재 인덱스
            curMax = Math.max(curNum, Math.max(tempMax, tempMin));
            curMin = Math.min(curNum, Math.min(tempMax, tempMin));

            ans = Math.max(ans, curMax);
        }

        return ans;
    }
}
