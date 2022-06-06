package from1to100;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/merge-k-sorted-lists/
public class Prob23MergeKSortedLists {
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

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for(int i=0;i<lists.length;i++){
            if(lists[i] != null) pq.offer(lists[i]);
        }

        ListNode curNode;
        while (!pq.isEmpty()) {
            curNode = pq.poll();

            cur.next = curNode;
            cur = cur.next;

            curNode = curNode.next;
            if (curNode != null) pq.offer(curNode);
        }

        return dummy.next;
    }
}
