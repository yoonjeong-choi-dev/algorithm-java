package from501to600;

import java.util.Stack;

// https://leetcode.com/problems/next-greater-element-ii/
public class Prob503NextGreaterElement2 {
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        Stack<Integer> stack = new Stack<>();

        // n-1 -> 0 -> n-1 -> 0 까지 탐색
        int curIdx = len - 1;
        for (int i = 2 * len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[curIdx]) {
                stack.pop();
            }
            ans[curIdx] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[curIdx]);

            curIdx--;
            if (curIdx < 0) curIdx = len - 1;
        }


        return ans;
    }
}
