package from1to100;

// https://leetcode.com/problems/reverse-linked-list-ii/
public class Prob92ReverseLinkedList2 {
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

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) return head;

        // left == 1 인 경우를 위해서
        ListNode ans = new ListNode(-1);
        ans.next = head;

        int reverseSize = right - left;

        // head -> .. -> prev -> (start -> .. -> end) -> next
        // => head -> .. -> prev -> (end -> ... -> start) -> next
        ListNode prev = ans, start = head;
        for (int i = 1; i < left; i++) {
            prev = start;
            start = start.next;
        }
        prev.next = null;

        ListNode end = start;
        for (int i = 0; i < reverseSize; i++) {
            end = end.next;
        }
        ListNode next = end.next;
        end.next = null;

        // reverse
        reverse(start);

        // connect
        prev.next = end;
        start.next = next;

        return ans.next;
    }

    // Ref : Problem 206
    private void reverse(ListNode head) {
        // prev -> cur 일 때, prev <- cur 형태로 변경 필요
        ListNode prev = null, cur = head;
        ListNode temp;

        while (cur != null) {
            // 다음 노드 임시 저장
            temp = cur.next;

            // prev <- cur
            cur.next = prev;

            prev = cur;
            cur = temp;
        }
    }
}
