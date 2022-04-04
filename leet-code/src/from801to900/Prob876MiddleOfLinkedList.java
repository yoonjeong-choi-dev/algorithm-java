package from801to900;

// https://leetcode.com/problems/middle-of-the-linked-list/
public class Prob876MiddleOfLinkedList {
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

    public ListNode middleNode(ListNode head) {
        int len = 0;

        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }

        // 중간값 계산
        // 홀수면 중간, 짝수면 중간+1
        // 0 부터 시작하므로 2로 나누면 끝!
        int mid = len / 2;
        for(int i=0;i<mid;i++){
            head = head.next;
        }

        return head;
    }
}
