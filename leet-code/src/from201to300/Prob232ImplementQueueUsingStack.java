package from201to300;

import java.util.Stack;

// https://leetcode.com/problems/implement-queue-using-stacks/
public class Prob232ImplementQueueUsingStack {
    // Implement a first in first out (FIFO) queue using only two stacks.
    class MyQueue {

        private final Stack<Integer> main = new Stack<>();
        private final Stack<Integer> temp = new Stack<>();

        public MyQueue() {

        }

        public void push(int x) {
            if (main.isEmpty()) {
                main.push(x);
                return;
            }

            while (!main.isEmpty()) {
                temp.push(main.pop());
            }

            main.push(x);
            while (!temp.isEmpty()) {
                main.push(temp.pop());
            }
        }

        public int pop() {
            return main.pop();
        }

        public int peek() {
            return main.peek();
        }

        public boolean empty() {
            return main.isEmpty();
        }

        // for test
        public String toString() {
            return "Reverse Of " + main.toString();
        }
    }

    public void testQueue() {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        System.out.println(myQueue);

        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue);

        System.out.printf("Peek : %d\n", myQueue.peek());

        System.out.printf("Pop : %d\n", myQueue.pop());
        System.out.println(myQueue);

        System.out.printf("Empty ? : %s\n", myQueue.empty());
    }

    public static void main(String[] args) {
        Prob232ImplementQueueUsingStack sol = new Prob232ImplementQueueUsingStack();
        sol.testQueue();
    }
}
