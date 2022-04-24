package from1to100;

// https://leetcode.com/problems/swap-nodes-in-pairs/
public class Prob24SwapNodesPair {
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

    public ListNode swapPairs(ListNode head) {
        ListNode ans = new ListNode(-1, head);
        ListNode prev = ans, cur = prev.next;
        ListNode next;

        while (cur != null && cur.next != null) {
            next = cur.next;

            prev.next = next;
            cur.next = next.next;
            next.next = cur;

            prev = cur;
            cur = cur.next;
        }

        return ans.next;
    }
}
