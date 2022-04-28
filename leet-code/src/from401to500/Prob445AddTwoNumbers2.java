package from401to500;

import java.util.Stack;

// https://leetcode.com/problems/add-two-numbers-ii/
public class Prob445AddTwoNumbers2 {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum, carry = 0;
        Stack<Integer> add = new Stack<>();
        while (!s1.isEmpty() && !s2.isEmpty()) {
            sum = carry + s1.pop() + s2.pop();
            if (sum > 9) {
                sum -= 10;
                carry = 1;
            } else carry = 0;

            add.push(sum);
        }

        if (s1.isEmpty()) s1 = s2;

        while (!s1.isEmpty()) {
            sum = carry + s1.pop();
            if (sum > 9) {
                sum -= 10;
                carry = 1;
            } else carry = 0;

            add.push(sum);
        }

        if (carry == 1) add.push(1);

        ListNode ans = new ListNode(-1);
        ListNode cur = ans;
        while (!add.isEmpty()) {
            cur.next = new ListNode(add.pop());
            cur = cur.next;
        }

        return ans.next;
    }
}
