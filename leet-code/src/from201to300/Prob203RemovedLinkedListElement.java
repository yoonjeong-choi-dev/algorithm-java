package from201to300;


// https://leetcode.com/problems/remove-linked-list-elements/
public class Prob203RemovedLinkedListElement {
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

    public ListNode removeElements(ListNode head, int val) {
        // 특이 케이스 : 헤드 노드를 제거하는 경우
        while (head != null && head.val == val) head = head.next;

        // 제거되는 노드는 헤드 노드가 아님
        // 제거되는 노드의 앞 노드 정보가 필요
        ListNode prev = head;
        while (prev != null) {
            // 마지막 노드까지 온 경우 탐색 종료
            if(prev.next == null) break;

            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }
}
