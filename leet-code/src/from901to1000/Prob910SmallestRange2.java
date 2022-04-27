package from901to1000;

import java.util.Arrays;

// https://leetcode.com/problems/smallest-range-ii/
// TODO : 다시 풀기
public class Prob910SmallestRange2 {
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);

        int len = nums.length;

        // 모두 k 를 더하거나 빼거나 할 때의 점수
        int ans = nums[len - 1] - nums[0];

        // 양끝이 다른 경우, 최대 및 최소를 줄이기 위해서는 가장 작은 값은 키우고 가장 큰 값은 줄여야 함
        // i.e 점수가 더 낮아질 수 있는 상황
        int minRet = nums[0] + k, maxRet = nums[len - 1] - k;

        // 점수가 더 낮아지게 하는 전략
        // nums[0]+k, nums[1]+k,....,nums[i]+k, nums[i+1]-k, nums[i+2]-k,...nums[len-1]-k 인 상황 고려
        // => nums[0]+k < nums[i]+k && nums[i+1]-k < nums[len-1]-k
        // nums[i]+k 가 최대값, nums[i+1]-k 가 최소값으로 변경
        int prev, next, minVal, maxVal;
        for (int i = 1; i < len; i++) {
            // 같은 값은 같이 더하거나 뺄 것이므로 무시
            if (nums[i - 1] == nums[i]) continue;

            // (prev+k, next-k) 는 (prev-k, next+k) 에 포함 => 범위를 줄여야 하므로 (prev+k, next-k) 만 고려해야함
            // (prev-k, next-k) 나 (prev+k, next+k) 의 경우 점수가 더 좋아지는 상황은 발생하지 않음
            prev = nums[i - 1];
            next = nums[i];

            // nums[i]+k 가 최대값, nums[i+1]-k 가 최소값으로 변경되는 상황인지 확인
            minVal = Math.min(minRet, next - k);
            maxVal = Math.max(maxRet, prev + k);
            ans = Math.min(maxVal - minVal, ans);
        }


        return ans;
    }

    public static void main(String[] args) {
        Prob910SmallestRange2 sol = new Prob910SmallestRange2();
        System.out.println(sol.smallestRangeII(new int[]{1, 2, 3, 4, 5}, 100));
    }
}
