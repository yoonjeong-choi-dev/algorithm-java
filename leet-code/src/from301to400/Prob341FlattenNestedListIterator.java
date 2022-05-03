package from301to400;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/flatten-nested-list-iterator/
public class Prob341FlattenNestedListIterator {
    interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }


    public class NestedIterator implements Iterator<Integer> {

        Stack<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            if(!hasNext()) throw new RuntimeException();
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            flattenNested();
            return !stack.isEmpty();
        }

        private void flattenNested() {
            while(!stack.isEmpty() && !stack.peek().isInteger()){
                List<NestedInteger> list = stack.pop().getList();

                for (int i = list.size() - 1; i >= 0; i--) {
                    stack.push(list.get(i));
                }
            }
        }
    }
}
