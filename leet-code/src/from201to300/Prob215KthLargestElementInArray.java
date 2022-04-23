package from201to300;

import java.util.PriorityQueue;
import java.util.Random;

// https://leetcode.com/problems/kth-largest-element-in-an-array/
public class Prob215KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        return solutionWithHeap(nums, k);
    }

    // 제한된 크기의 힙
    // O(N * log(k))
    private int solutionWithHeap(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int n : nums) {
            if (heap.size() < k) heap.offer(n);
            else if (n > heap.peek()) {
                heap.poll();
                heap.offer(n);
            }
        }

        return heap.peek();
    }

    // Quick Select 알고리즘
    int[] nums;
    int k;

    private int solutionWithQuickSelect(int[] nums, int k) {
        this.nums = nums;
        this.k = nums.length - k;

        return quickSelect(0, nums.length - 1);
    }

    // 퀵 정렬과 마찬가지로 피봇을 기준으로 두 개의 리스트로 분할
    // 피봇의 위치 반환 : nums[start:pivotIdx-1], nums[pivotIdx, end]
    private int partition(int start, int end, int pivotIdx) {
        int pivotVal = nums[pivotIdx];

        // 피봇을 가장 끝으로 이동
        arraySwap(pivotIdx, end);

        // 피봇보다 작은 숫자들은 왼쪽으로, 큰 숫자들은 오른쪽으로 이동하여 분할
        // 피봇보다 작은 숫자들을 왼쪽의 요소와 교환하는 방식으로 왼쪽 리스트를 만드는 방식
        int curIdx = start;
        for (int i = start; i < end; i++) {
            if (nums[i] < pivotVal) {
                arraySwap(curIdx, i);
                curIdx++;
            }
        }

        // 가장 끝으로 이동하였던 피봇을 중간으로 이동
        arraySwap(curIdx, end);
        return curIdx;
    }

    // 재귀 호출을 이용
    // partition 함수가 오름차순으로 정렬되어 있으므로, 해당 함수는 K 번째 작은 숫자 반환
    private int quickSelect(int start, int end) {
        if (start == end) return nums[start];

        // 랜덤하게 피봇 만들기
        int pivotIdx = new Random().nextInt(end - start) + start;
        pivotIdx = partition(start, end, pivotIdx);

        if (pivotIdx == k) return nums[k];
        else if (pivotIdx < k) {
            // 왼쪽 파티션의 크기가 k 보다 작은 경우 => 오른쪽 파티션 검색
            return quickSelect(pivotIdx + 1, end);
        } else {
            // 왼쪽 파티션의 크기가 k 보다 큰 경우 => 왼쪽 파티션 검색
            return quickSelect(start, pivotIdx - 1);
        }
    }


    private void arraySwap(int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}
