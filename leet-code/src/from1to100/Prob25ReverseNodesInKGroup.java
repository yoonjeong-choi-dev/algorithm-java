package from1to100;

// https://leetcode.com/problems/reverse-nodes-in-k-group/
public class Prob25ReverseNodesInKGroup {
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

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) return head;

        int curSize = 0;
        ListNode cur = head;
        while (cur != null) {
            curSize++;
            cur = cur.next;
        }

        ListNode dummy = new ListNode(-1, head);
        ListNode curHead = dummy, curTail;


        cur = curHead.next;
        ListNode prev, tail, next;

        while (cur != null) {
            if (curSize < k) break;

            // 다음에 탐색할 노드
            curTail = cur;
            for(int i=0;i<k;i++) curTail = curTail.next;

            // 현재 그룹 변경 후, 꼬리 노드
            tail = cur;

            // 현재 그룹에 대한 순서 변경
            prev = cur;
            cur = cur.next;
            for (int i = 1; i < k; i++) {
                // curHead->node1(prev)->node2(cur)->node3->node4
                // => curHead->node2(prev)->node1->node3(cur)
                // => curHead->node3->node2->node1
                // i.e curHead -> prev -> ....->  cur -> next
                // => curHead -> cur -> prev -> ....-> next
                next = cur.next;

                // curHead -> prev 사이에 cur 삽입
                curHead.next = cur;
                cur.next = prev;

                prev = cur;
                cur = next;
            }

            tail.next = curTail;
            curHead = tail;
            cur = curHead.next;
            curSize -= k;
        }

        return dummy.next;
    }
}
