package from201to300;

// https://leetcode.com/problems/delete-node-in-a-linked-list/
public class Prob237DeleteNodeInLinkedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void deleteNode(ListNode node) {
        // node를 node.next로 대체
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
    }
}
