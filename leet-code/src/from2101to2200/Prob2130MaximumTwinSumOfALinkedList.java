package from2101to2200;

// https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
public class Prob2130MaximumTwinSumOfALinkedList {
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

    public int pairSum(ListNode head) {
        // The number of nodes in the list is even
        ListNode slow = head, fast = head.next;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse right
        ListNode prev = null, cur = slow.next, temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = prev;

            prev = cur;
            cur = temp;
        }

        ListNode left = head, right = prev;
        int ans = 1;
        while(right!=null) {
            ans = Math.max(ans, left.val + right.val);
            left = left.next;
            right = right.next;
        }
        return ans;
    }
}
