package from201to300;

// https://leetcode.com/problems/reverse-linked-list/submissions/
public class Prob206ReverseLinkedList {
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

    public ListNode reverseList(ListNode head) {
        // prev -> cur 일 때, prev <- cur 형태로 변경 필요
        ListNode prev = null, cur = head;
        ListNode temp;
        while (cur != null) {
            // 탐색해야 하는 다음 노드를 임시 저장 : 현재 노드의 방향(next 필드)를 변경시켜야 하기 때문
            temp = cur.next;

            // 현재 노드 방향 변경
            cur.next = prev;

            // 다음 노드로 이동
            prev = cur;
            cur = temp;
        }
        return prev;
    }
}
