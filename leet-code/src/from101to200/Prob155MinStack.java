package from101to200;

import java.util.Stack;

// https://leetcode.com/problems/min-stack/
public class Prob155MinStack {
    class MinStack {

        // [value, curMin]
        private final Stack<int[]> minStack = new Stack<>();

        public MinStack() {

        }

        public void push(int val) {
            if(minStack.isEmpty()) {
                minStack.push(new int[]{val, val});
            } else {
                int curMin = minStack.peek()[1];
                minStack.push(new int[]{val, Math.min(val, curMin)});
            }
        }

        public void pop() {
            if (minStack.isEmpty()) return;

            minStack.pop();
        }

        public int top() {
            return minStack.peek()[0];
        }

        public int getMin() {
            return minStack.peek()[1];
        }
    }
}
