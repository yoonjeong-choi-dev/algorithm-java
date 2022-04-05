package from401to500;

import java.util.HashMap;
import java.util.Stack;

// https://leetcode.com/problems/next-greater-element-i/
public class Prob496NextGreaterElement1 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        return mySquareSolution(nums1, nums2);
    }

    // Runtime: 6 ms, faster than 39.42% of Java online submissions
    private int[] mySquareSolution(int[] nums1, int[] nums2) {
        int n = nums2.length;
        int pos;
        for (int i = 0; i < nums1.length; i++) {
            pos = 0;

            while (pos < n && nums1[i] != nums2[pos]) pos++;
            while (pos < n && nums1[i] >= nums2[pos]) pos++;

            nums1[i] = (pos == n) ? -1 : nums2[pos];
        }

        return nums1;
    }

    // TODO : Improve - Linear Solution
    private int[] linearSolution(int[] nums1, int[] nums2) {
        // nextGreater : (현재 숫자, 다음 큰 숫자)
        HashMap<Integer, Integer> nextGreater = new HashMap<>();

        // nextGreater 정보 저장을 위한 스택
        Stack<Integer> remainders = new Stack<>();
        for (int num : nums2) {
            // 현재 숫자 nums2[i]보다 작은 값들을 스택에서 뽑아 맵에 저장
            while (!remainders.isEmpty() && num > remainders.peek()) {
                nextGreater.put(remainders.pop(), num);
            }

            // 현재 숫자 저장
            remainders.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {
            // 맵에 없는 숫자들은 다음 큰수가 없음 => -1
            nums1[i] = nextGreater.getOrDefault(nums1[i], -1);
        }

        return nums1;
    }
}
