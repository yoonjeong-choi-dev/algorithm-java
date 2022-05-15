package from201to300;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/sliding-window-maximum/
public class Prob239SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int size = nums.length;
        int[] ans = new int[size - k + 1];

        // [i*k, i*k+k-1] 블록에 대해서 죄측 최대값과 우측 최대값 저장
        // rightMax[i*k + a] : [i*k, i+k+a] 부분 배열의 최대값 => 블록의 오른쪽 부분에서의 최대값
        // leftMax[i*k+k-1-a] : [i*k+k-1-a, i*k+k-1] 부분 배열의 최대값 => 블록의 왼쪽 부분에서의 최대값
        // => nums[left, right] 윈도우에 대한 연산 : left 는 i 번째 블록, right 는 (i+1) 번째 블록인 경우
        // => i.e left = i+k +a, right = left+k-1 = i+(k+1) + b
        // => max(right[left], left[right])

        int[] leftMax = new int[size];
        leftMax[0] = nums[0];
        int[] rightMax = new int[size];
        rightMax[size - 1] = nums[size - 1];

        int reverse;
        for (int i = 1; i < size; i++) {
            // leftMax 연산
            if (i % k == 0) leftMax[i] = nums[i];
            else leftMax[i] = Math.max(leftMax[i - 1], nums[i]);

            // rightMax 연산
            reverse = size - 1 - i;
            if ((reverse + 1) % k == 0) rightMax[reverse] = nums[reverse];
            else rightMax[reverse] = Math.max(rightMax[reverse + 1], nums[reverse]);
        }

        for (int i = 0; i < ans.length; i++) {
            ans[i] = Math.max(rightMax[i], leftMax[i + k - 1]);
        }


        return ans;
    }

    // TLE : 힙 삭제 연산 n => O(n^2)
    private int[] solutionWithHeap(int[] nums, int k) {
        int size = nums.length;
        int[] ans = new int[size - k + 1];
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());

        // 첫 K-1 개 저장
        for (int i = 0; i < k - 1; i++) heap.add(nums[i]);

        for (int i = k - 1; i < size; i++) {
            // 현재 숫자 저장
            heap.add(nums[i]);

            // 현재 윈도우에서 최대값 저장
            ans[i - k + 1] = heap.peek();

            // 다음을 위해 윈도우 가장 왼쪽 숫자 제거
            heap.remove(nums[i - k + 1]);
        }

        return ans;
    }
}
