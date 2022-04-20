package from501to600;

import java.util.HashMap;

// https://leetcode.com/problems/subarray-sum-equals-k/
public class Prob560SubArraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        return partialSumSolution(nums, k);
    }

    // Solution 1 : O(n^2)
    private int bruteForce(int[] nums, int k) {
        int len = nums.length;
        int ans = 0;
        int curSum;

        // nums[start] ~ nums[end] 까지 합 구하기
        for (int start = 0; start < len; start++) {
            curSum = 0;
            for (int end = start; end < len; end++) {
                curSum += nums[end];
                if (curSum == k) ans++;
            }
        }

        return ans;
    }

    // Solution 2 : O(n^2) with Partial Sum
    private int partialSumSolution(int[] nums, int k) {
        int len = nums.length;
        int[] partialSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            partialSum[i + 1] = partialSum[i] + nums[i];
        }

        int ans = 0;
        for (int start = 0; start < len; start++) {
            for (int end = start + 1; end <= len; end++) {
                if (partialSum[end] - partialSum[start] == k) ans++;
            }
        }

        return ans;
    }

    // TODO - Solution 3 : Use HashMap
    private int hashMapSolution(int[] nums, int k) {
        // nums[:i] 까지의 합
        int curSum = 0;

        // (sum, numOccurrence)
        HashMap<Integer, Integer> map = new HashMap<>();

        // 나머지가 0인 경우에는 1이 추가됨
        map.put(0, 1);

        int ans = 0;
        int remain;
        for (int num : nums) {
            curSum += num;
            remain = curSum - k;
            if (map.containsKey(remain)) {
                ans += map.get(remain);
            }

            map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        }

        return ans;
    }
}
