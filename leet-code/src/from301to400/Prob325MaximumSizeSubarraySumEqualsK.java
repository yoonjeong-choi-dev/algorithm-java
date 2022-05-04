package from301to400;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
public class Prob325MaximumSizeSubarraySumEqualsK {
    public int maxSubArrayLen(int[] nums, int k) {
        int ans = 0;

        // sum nums[0:i]
        int curSum = 0;

        // (value : nums[0:i] 합, i)
        Map<Integer, Integer> history = new HashMap<>();

        int overVal;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];

            if (curSum == k) {
                ans = Math.max(ans, i + 1);
            } else {
                // 앞에서 빼줘야 하는 값 : k = curSum - overVal
                overVal = curSum - k;
                if (history.containsKey(overVal)) {
                    ans = Math.max(ans, i - history.get(overVal));
                }
            }

            // 현재 합이 없는 경우에만 저장 : 최대 길이를 구해야 하기 때문에 최초 한번만 저장
            if (!history.containsKey(curSum)) history.put(curSum, i);
        }

        return ans;
    }

    public static void main(String[] args) {
        Prob325MaximumSizeSubarraySumEqualsK sol = new Prob325MaximumSizeSubarraySumEqualsK();

        int[][] nums = {
                {1, -1, 5, -2, 3},
                {-2, -1, 2, 1}
        };
        int[] k = {3, 1};

        int[] ans = {4, 2};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.maxSubArrayLen(nums[i], k[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
