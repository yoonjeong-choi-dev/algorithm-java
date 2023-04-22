package from201to300;

// https://leetcode.com/problems/product-of-array-except-self/
public class Prob238ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;

        // 문제 조건 : The output array does not count as extra space for space complexity analysis
        int[] ans = new int[len];

        int zeroIdx = -1;

        // 배열 전체에 대한 곱 : 길이 * 최대 요소 = 10^5 * 30 = 3 * 10^6 => int 형으로 저장 가능
        int nonZeroProduct = 1;
        for (int i = 0; i < len; i++) {
            // 해당 요소가 0 인 경우
            if (nums[i] == 0) {
                if (zeroIdx != -1) {
                    // 0인 요소가 2개 이상인 경우 모두 0
                    return ans;
                }
                zeroIdx = i;
            } else {
                nonZeroProduct *= nums[i];
            }
        }

        // 0인 요소가 1개 존재 하는 경우
        // => 해당 요소 위치를 제외하고는 모두 0
        if (zeroIdx != -1) {
            ans[zeroIdx] = nonZeroProduct;
            return ans;
        }

        for (int i = 0; i < len; i++) {
            ans[i] = nonZeroProduct / nums[i];
        }

        return ans;
    }


}
