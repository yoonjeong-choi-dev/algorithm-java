package from401to500;

import java.util.Stack;

// https://leetcode.com/problems/132-pattern/
public class Prob456OneThreeTwoPattern {
    public boolean find132pattern(int[] nums) {
        int size = nums.length;

        if (size < 3) return false;

        // min[i] : nums[0:i] 부분배열의 최소값 => 132 패턴의 1에 해당
        int[] min = new int[size];
        min[0] = nums[0];
        for (int i = 1; i < size; i++) min[i] = Math.min(min[i - 1], nums[i]);

        Stack<Integer> stack = new Stack<>();
        for (int i = size-1; i >= 0; i--) {
            // 132 패턴에서 3에 해당하는 부분 탐색
            if (nums[i] <= min[i]) continue;

            // 현재 최소값보다 작은 숫자들 스택에서 제거
            // => 스택의 나머지 부분이 2에 해당하는 후보들
            while (!stack.isEmpty() && stack.peek() <= min[i]) stack.pop();

            // 이전 숫자 중에 현재 숫자보다 작은 숫자 있으면 (2에 해당) 참
            if(!stack.isEmpty() && stack.peek() < nums[i]) return true;


            // 현재 숫자 스택에 저장
            stack.push(nums[i]);
        }


        return false;
    }

}
