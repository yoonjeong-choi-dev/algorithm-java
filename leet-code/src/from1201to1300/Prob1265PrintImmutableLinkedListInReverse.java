package from1201to1300;

import java.util.Stack;

// https://leetcode.com/problems/print-immutable-linked-list-in-reverse/
public class Prob1265PrintImmutableLinkedListInReverse {
    interface ImmutableListNode {
        void printValue();

        ImmutableListNode getNext();
    }

    public void printLinkedListInReverse(ImmutableListNode head) {
        Stack<ImmutableListNode> stack = new Stack<>();
        while (head != null) {
            stack.add(head);
            head = head.getNext();
        }

        while (!stack.isEmpty()) {
            stack.pop().printValue();
        }
    }
}
