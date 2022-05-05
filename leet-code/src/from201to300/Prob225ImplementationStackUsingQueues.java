package from201to300;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/implement-stack-using-queues/
public class Prob225ImplementationStackUsingQueues {
    class MyStack {

        private final Queue<Integer> store1 = new LinkedList<>();
        private final Queue<Integer> store2 = new LinkedList<>();
        private Queue<Integer> curStack, curStore;

        public MyStack() {
            curStack = store1;
            curStore = store2;
        }

        public void push(int x) {

            if (!curStack.isEmpty()) {
                while (!curStack.isEmpty()) {
                    curStore.add(curStack.poll());
                }
            }
            curStack.add(x);
        }

        public int pop() {
            int ret = curStack.poll();
            while (curStore.size() > 1) {
                curStack.add(curStore.poll());
            }

            Queue<Integer> temp = curStack;
            curStack = curStore;
            curStore = temp;

            return ret;
        }

        public int top() {
            return curStack.peek();
        }

        public boolean empty() {
            return store1.isEmpty() && store2.isEmpty();
        }
    }
}
