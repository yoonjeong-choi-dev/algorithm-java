package from501to600;

import java.util.Arrays;
import java.util.Stack;

// https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
public class Prob581ShortestUnsortedContinuousSubarray {
    public int findUnsortedSubarray(int[] nums) {
        //return solutionWithSort(nums);
        return solutionWithStack(nums);
    }

    // O(n*log(n))
    private int solutionWithSort(int[] nums) {
        int[] sorted = Arrays.stream(nums).sorted().toArray();

        int len = nums.length;
        int left = 0, right = len - 1;
        while (left < len && nums[left] == sorted[left]) left++;
        if (left == len) return 0;

        while (right >= 0 && nums[right] == sorted[right]) right--;

        return right - left + 1;
    }

    // O(n)
    private int solutionWithStack(int[] nums) {
        int len = nums.length;

        // [0,left-1] 및 [right+1, len-1] 은 오름차순
        // [left, right] 숫자들은 nums[left-1] 이상, nums[right +1] 이하 값들
        // => left : 0 인덱스부터 시작해서 오름차순을 벗어나는 인덱스들 중 가장 작은 값
        // => right : len-1 인덱스부터 시작해서 내림차순을 벗어나는 인덱스 들 중 가장 큰 값
        int left = len - 1, right = 0;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            // 현재 수보다 큰 수가 스택에 있는 경우 i.e 이전 수 중에 현재 수보다 큰 수 존재
            // => 해당 수들의 인덱스들의 최소값
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                left = Math.min(left, stack.pop());
            }
            stack.push(i);
        }

        if (left == len - 1) return 0;

        stack.clear();
        for (int i = len - 1; i >= 0; i--) {
            // 현재 수자보다 작은 수가 스택에 있는 경우 i.e 이전 숫자 중에 현재 수보다 작은 수 존재
            // => 해당 수들의 인덱스들의 최대값
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                right = Math.max(right, stack.pop());
            }
            stack.push(i);
        }

        return right - left + 1;
    }
}
