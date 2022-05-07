package from601to700;

// https://leetcode.com/problems/design-circular-queue/
public class Prob622DesignCircularQueue {
    class MyCircularQueue {

        private final int[] queue;
        private int front, rear;

        // empty,full 상태 모두 front==rear 이므로 따로 저장
        private int size;
        private final int capacity;

        public MyCircularQueue(int k) {
            queue = new int[k];
            capacity = k;
            size = 0;

            // front : 큐의 첫번째 요소 바로 앞 인덱스
            // => poll 연산 시, front 요소 반환 및 front++
            front = k - 1;

            // rear : 큐의 마지막 요소
            // => add 연산 시, rear+1 요소에 저장
            rear = k - 1;
        }

        public boolean enQueue(int value) {
            if (isFull()) return false;

            rear = (rear + 1) % capacity;
            queue[rear] = value;

            size++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;

            front = (front + 1) % capacity;

            size--;
            return true;
        }

        public int Front() {
            if (isEmpty()) return -1;

            return queue[(front + 1) % capacity];
        }

        public int Rear() {
            if (isEmpty()) return -1;
            return queue[rear];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }
    }
}
