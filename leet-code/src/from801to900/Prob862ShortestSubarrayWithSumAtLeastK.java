package from801to900;

import java.util.Deque;
import java.util.LinkedList;

// https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
// TODO : 다시 풀어보기
public class Prob862ShortestSubarrayWithSumAtLeastK {
    public int shortestSubarray(int[] nums, int k) {
        int size = nums.length;

        // sum nums[i:j] = partialSum[j+1] - partialSum[i]
        long[] partialSum = new long[size + 1];
        for (int i = 0; i < size; i++) {
            partialSum[i + 1] = partialSum[i] + (long) nums[i];
        }

        int ans = Integer.MAX_VALUE;

        // 시작 인덱스 저장 : partialSum 에 대해 오름차순으로 저장됨
        Deque<Integer> deque = new LinkedList<>();

        // 끝점을 기준으로 탐색
        // end : 현재 후보가 되는 끝점(end-1)
        // => partialSum[end] - partialSum[start]>=k 인 start 인덱스를 덱의 앞에서 뽑아옴
        for (int end = 0; end <= size; end++) {
            // 현재 누적합보다 큰 시작점 후보들 제거
            // k>0 이고, 현재 누적합보다 큰 시작점 후보들에 대해서는 0이하의 부분합이 나오므로 삭제
            // 이후 끝점들에 대해서도 최소 길이를 구하기 때문에 삭제된 시작점보다 현재 end 값을 시작점으로 해야함
            while (!deque.isEmpty() && partialSum[end] <= partialSum[deque.getLast()]) {
                deque.removeLast();
            }

            // 현재 저장되어 있는 시작점 후보 중 가능한 경우 정답 업데이트
            while(!deque.isEmpty() && partialSum[end] >= partialSum[deque.getFirst()] + k) {
                // 최소 길이를 구해야하므로 사용한 시작점은 버림
                // deque.removeFirst() ~ (end-1) 길이 : end - 1 - deque.removeFirst() +1 = end - deque.removeFirst()
                ans = Math.min(ans, end - deque.removeFirst());
            }

            deque.addLast(end);
        }


        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
