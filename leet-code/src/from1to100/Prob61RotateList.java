package from1to100;

// https://leetcode.com/problems/rotate-list/
public class Prob61RotateList {
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


    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;

        // 우선 꼬리 노드를 찾아 머리에 연결
        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        // k 범위를 len 이하로 변경
        k = k % len;
        tail.next = head;
        for (int i = 0; i < len - k; i++) {
            head = head.next;
            tail = tail.next;
        }

        // 꼬리 -> 머리 연결 제거
        tail.next = null;
        return head;
    }
}
