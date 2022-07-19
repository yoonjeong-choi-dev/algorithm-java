package from301to400;

// https://leetcode.com/problems/odd-even-linked-list/
public class Prob328OddEvenLinkedList {
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

    public ListNode oddEvenList(ListNode head) {
        // Assumption : size >= 2
        if(head == null || head.next == null) return head;

        // 짝수 노드들 저장하는 더미 헤드
        ListNode evenHead = new ListNode(-1);

        // // cur : odd, cur.next : even
        ListNode cur = head, even = evenHead, nextOdd;
        while (cur.next != null) {
            // 홀수 노드 업데이트
            even.next = cur.next;
            even = even.next;

            // cur -> cur.next.next 연결 및 cur.next 삭제
            nextOdd = cur.next.next;
            cur.next = nextOdd;
            even.next = null;

            // 다음 홀수 노드가 없는 경우 멈춤
            if (nextOdd == null) break;

            // 다음 홀수 노드가 있는 경우 계산
            cur = nextOdd;
        }

        // cur : 홀수 리스트의 tail
        cur.next = evenHead.next;
        return head;
    }
}
