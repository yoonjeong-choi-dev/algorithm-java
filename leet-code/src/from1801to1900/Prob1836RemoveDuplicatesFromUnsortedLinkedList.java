package from1801to1900;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list/
public class Prob1836RemoveDuplicatesFromUnsortedLinkedList {
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

    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        Map<Integer, Integer> counter = new HashMap<>();
        ListNode cur = head;
        while (cur != null) {
            counter.put(cur.val, counter.getOrDefault(cur.val, 0) + 1);
            cur = cur.next;
        }

        // Delete duplicates
        ListNode ans = new ListNode(-1, head);
        ListNode prev = ans, temp;
        cur = head;
        while (cur != null) {
            if (counter.get(cur.val) > 1) {
                // prev -> cur -> cur.next
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return ans.next;
    }
}
