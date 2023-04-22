package from601to700;

// https://leetcode.com/problems/design-circular-deque/
public class Prob641DesignCircularDeque {
    class MyCircularDeque {
        private int[] data;
        private int size, head, tail, capacity;

        public MyCircularDeque(int k) {
            data = new int[k];
            capacity = k;
            size = 0;
            head = 0;
            tail = capacity - 1;
        }

        public boolean insertFront(int value) {
            if (isFull()) return false;

            data[head] = value;
            head = (head + 1) % capacity;
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) return false;

            data[tail] = value;
            tail = (tail - 1 + capacity) % capacity;
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) return false;

            head = (head - 1 + capacity) % capacity;
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) return false;

            tail = (tail + 1) % capacity;
            size--;
            return true;
        }

        public int getFront() {
            if (isEmpty()) return -1;
            return data[(head - 1 + capacity) % capacity];
        }

        public int getRear() {
            if (isEmpty()) return -1;
            return data[(tail + 1) % capacity];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }
    }
}
