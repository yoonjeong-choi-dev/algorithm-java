package from101to200;

// https://leetcode.com/problems/reorder-list/
public class Prob143ReorderList {
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

    public void reorderList(ListNode head) {
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }

        if (size == 1) return;

        int leftSize = size / 2 + size % 2;
        cur = head;
        for (int i = leftSize - 1; i > 0; i--) cur = cur.next;

        // head->... ->cur 및 cur.next -> .. 로 나눔
        ListNode right = new ListNode(-1, cur.next);
        cur.next = null;

        // reverse right
        // Ref : Problem 25
        ListNode prev = right.next, tail = right.next, temp;
        cur = prev.next;
        while (cur != null) {
            // right -> prev -> .. -> cur -> nextCur => right -> cur(nextPrev) -> prev-> ... -> nextCur
            temp = cur.next;

            right.next = cur;
            cur.next = prev;

            prev = cur;
            cur = temp;
        }

        tail.next = null;

        cur = head;
        right = right.next;
        while (right != null) {
            temp = cur.next;
            cur.next = right;
            cur = temp;

            temp = right.next;
            right.next = cur;
            right = temp;
        }
    }
}
