package from2001to2100;

// https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
public class Prob2095DeleteTheMiddleNodeOfALinkedList {
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

    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null) return null;

        ListNode slow = head, fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // slow.next : middle node
        slow.next = slow.next.next;
        return head;
    }
}
