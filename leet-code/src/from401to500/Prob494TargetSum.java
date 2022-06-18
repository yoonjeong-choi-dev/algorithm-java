package from401to500;

// https://leetcode.com/problems/target-sum/
public class Prob494TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        //return recursiveSolution(nums, target);
        return dp(nums, target);
    }

    private int ans, target, len;
    private int[] nums;

    // nums.length <= 20
    // 모든 경우의 수는 pow(2,20) = 10^6 => 완전 탐색 가능
    private int recursiveSolution(int[] nums, int target) {
        ans = 0;
        this.nums = nums;
        this.len = nums.length;
        this.target = target;

        recursive(0, 0);
        return ans;
    }

    private void recursive(int idx, int curSum) {
        if (idx == len) {
            if (curSum == target) ans++;
            return;
        }

        recursive(idx + 1, curSum + nums[idx]);
        recursive(idx + 1, curSum - nums[idx]);
    }

    private int dp(int[] nums, int target) {
        int len = nums.length;

        int totalSum = 0;
        for (int num : nums) totalSum += num;

        // 0 <= nums[i] <= 1000 && 0 <= sum(nums[i]) <= 1000
        // 최대값은 모두 다 더한 totalSum, 최소값은 모두 다 뺀 -totalSum
        // => [-totalSum,totalSum] 범위 안에 모든 경우가 다 들어가 있음
        int dpSize = 2 * totalSum + 1;

        // dp[i] = (i-totalSum) 에 대한 경우의 수
        // <=> target 에 대한 경우의 수 : dp[target+totalSum]
        int[] dp = new int[dpSize];

        // 첫번째 단계 업데이트
        // 0인 경우에는 경우의 수가 2이므로 대입 대신 += 연산 사용
        dp[nums[0] + totalSum] += 1;
        dp[-nums[0] + totalSum] += 1;

        // 두번째 요소부터 bottom-up
        int[] next;
        for (int level = 1; level < len; level++) {
            // next : 다음 단계에 대한 dp
            next = new int[dpSize];

            for (int i = 0; i < dpSize; i++) {
                // nums[:i] 까지의 부분 문제에 대해 유효한 경로가 있는 경우
                // 유효한 경로 내에서는 i + nums[level] 및 i - nums[level] 값이 dp 범위 안에 들어감
                // i.e (totalSum + i + nums[level]), (totalSum - i + nums[level]) 가 [-totalSum,totalSum] 안에 들어감
                // => 유요한 인덱스
                if (dp[i] != 0) {
                    next[i + nums[level]] += dp[i];
                    next[i - nums[level]] += dp[i];
                }
            }

            dp = next;
        }

        return Math.abs(target) <= totalSum ? dp[target + totalSum] : 0;
    }
}
