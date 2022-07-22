package from1to100;

// https://leetcode.com/problems/partition-list/
public class Prob86PartitionList {
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

    public ListNode partition(ListNode head, int x) {
        ListNode ans = new ListNode(-1, head);
        ListNode tails = new ListNode(-1);

        ListNode prev = ans, cur = ans.next;
        ListNode tail = tails;

        while (cur != null) {
            if (cur.val >= x) {
                // delete cur
                // prev -> cur -> next => prev -> next
                prev.next = cur.next;

                // add cur to tail
                tail.next = cur;
                cur.next = null;
                tail = tail.next;

                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }

        prev.next = tails.next;
        return ans.next;
    }
}
