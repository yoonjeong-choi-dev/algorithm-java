package from901to1000;

import java.util.Stack;

// https://leetcode.com/problems/sum-of-subarray-minimums/
public class Prob907SumOfSubArrayMinimums {
    public int sumSubarrayMins(int[] arr) {
        int ans = 0;

        final int MOD = 1000000007;
        int len = arr.length;

        // dp[i] : arr[0:i] 부분 문제에 대한 정답
        int[] dp = new int[len];

        Stack<Integer> monoStack = new Stack<>();
        for (int i = 0; i < len; i++) {
            // 현재 숫자보다 큰 앞 인덱스 삭제
            while (!monoStack.isEmpty() && arr[monoStack.peek()] > arr[i]) monoStack.pop();


            if (monoStack.isEmpty()) {
                // 스택이 비어 었는 경우 : arr[0:i] 중 arr[i] 가 최소값
                // => arr[i]로 끝나는 모든 부분 배열에 대한 최소값은 arr[i]
                dp[i] = (i + 1) * arr[i];
            } else {
                // 스택이 남아 있는 경우 : p = stack.peek()
                // arr[p+1:i] 중 최소값은 arr[i], arr[:p] 에대 한 정답은 dp 이용
                dp[i] = (i - monoStack.peek()) * arr[i] + dp[monoStack.peek()];
            }
            ans = (ans + dp[i]) % MOD;
            monoStack.push(i);
        }

        return ans;
    }
}
