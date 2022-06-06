package from201to300;

import java.util.Collections;
import java.util.PriorityQueue;

// https://leetcode.com/problems/find-median-from-data-stream/
public class Prob295FindMedianFromDataStream {
    class MedianFinder {

        private final PriorityQueue<Integer> leftHeap;
        private final PriorityQueue<Integer> rightHeap;

        public MedianFinder() {
            // median 왼쪽 숫자 저장 => max heap
            leftHeap = new PriorityQueue<>(Collections.reverseOrder());

            // median 오른쪽 숫자 저장 => min heap
            rightHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            // 우선 왼쪽에 저장
            leftHeap.offer(num);

            // 왼쪽에 있는 가장 큰 값을 오른쪽으로 옮김
            rightHeap.offer(leftHeap.poll());

            // 항상 왼쪽이 오른쪽보다 많도록 함
            // 2n개가 있는 경우 각각 n개 씩
            // 2n+1개가 있는 경우 각각 n+1, n개씩
            if (leftHeap.size() < rightHeap.size()) {
                leftHeap.offer(rightHeap.poll());
            }
        }

        public double findMedian() {
            // 사이즈가 짝수면 두 힙의 평균
            // 사이즈가 홀수(2n+1)면 leftHeap 사이즈가 n+1이므로 leftHeap 숫자 반환
            return (leftHeap.size() == rightHeap.size()) ? ((double) leftHeap.peek() + rightHeap.peek()) * 0.5 : leftHeap.peek();
        }
    }
}
